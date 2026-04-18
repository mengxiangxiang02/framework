import pytest
import subprocess
import time
import signal
import sys
import os


@pytest.fixture(scope="session")
def flask_server():
    """Start the Flask application on port 8080 for the test session."""
    env = os.environ.copy()
    proc = subprocess.Popen(
        [sys.executable, "app.py"],
        cwd=os.path.dirname(__file__),
        env=env,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
    )
    for _ in range(30):
        time.sleep(0.5)
        try:
            import urllib.request
            urllib.request.urlopen("http://localhost:8080/login")
            break
        except Exception:
            continue
    else:
        proc.terminate()
        raise RuntimeError("Flask server did not start in time")

    yield proc

    proc.send_signal(signal.SIGTERM)
    try:
        proc.wait(timeout=5)
    except subprocess.TimeoutExpired:
        proc.kill()


@pytest.fixture(scope="session")
def base_url(flask_server):
    return "http://localhost:8080"
