from flask import Flask, render_template, request, redirect, url_for, session, flash
import functools

app = Flask(__name__)
app.secret_key = "test-secret-key-for-automation"

USERS = {
    "admin": "admin123",
    "user": "user123",
}


def login_required(view):
    @functools.wraps(view)
    def wrapped(**kwargs):
        if "username" not in session:
            return redirect(url_for("login", redirect=request.path))
        return view(**kwargs)
    return wrapped


@app.route("/login", methods=["GET", "POST"])
def login():
    redirect_url = request.args.get("redirect", "/")
    if request.method == "POST":
        username = request.form.get("username", "").strip()
        password = request.form.get("password", "").strip()
        redirect_url = request.form.get("redirect", "/")

        if not username:
            flash("请输入用户名", "error")
            return render_template("login.html", redirect=redirect_url)
        if not password:
            flash("请输入密码", "error")
            return render_template("login.html", redirect=redirect_url, username=username)
        if username not in USERS:
            flash("用户不存在", "error")
            return render_template("login.html", redirect=redirect_url, username=username)
        if USERS[username] != password:
            flash("密码错误", "error")
            return render_template("login.html", redirect=redirect_url, username=username)

        session["username"] = username
        return redirect(redirect_url)

    return render_template("login.html", redirect=redirect_url)


@app.route("/system/user")
@login_required
def system_user():
    return render_template("system_user.html", username=session["username"])


@app.route("/logout")
def logout():
    session.clear()
    return redirect(url_for("login"))


@app.route("/")
def index():
    return redirect(url_for("login"))


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8080)
