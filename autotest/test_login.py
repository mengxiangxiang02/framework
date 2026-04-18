"""
Automated tests for http://localhost/login?redirect=%2Fsystem%2Fuser

Test coverage:
  1. Page loads correctly with expected UI elements
  2. Empty form submission shows validation error
  3. Missing password shows error
  4. Non-existent user shows error
  5. Wrong password shows error
  6. Successful login redirects to /system/user
  7. /system/user requires authentication (redirect guard)
  8. Logout returns user to login page
  9. Redirect parameter is preserved through login flow
"""

import pytest
import re
from playwright.sync_api import Page, expect


LOGIN_URL = "/login?redirect=%2Fsystem%2Fuser"


class TestLoginPageUI:
    """Tests for login page rendering and UI elements."""

    def test_page_loads(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        expect(page).to_have_title("系统登录")

    def test_has_username_input(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        username = page.get_by_test_id("username-input")
        expect(username).to_be_visible()
        expect(username).to_have_attribute("placeholder", "请输入用户名")

    def test_has_password_input(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        password = page.get_by_test_id("password-input")
        expect(password).to_be_visible()
        expect(password).to_have_attribute("type", "password")
        expect(password).to_have_attribute("placeholder", "请输入密码")

    def test_has_login_button(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        btn = page.get_by_test_id("login-button")
        expect(btn).to_be_visible()
        expect(btn).to_contain_text("登 录")

    def test_heading_visible(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        heading = page.locator("h1")
        expect(heading).to_have_text("系统登录")


class TestLoginValidation:
    """Tests for form validation and error messages."""

    def test_empty_form_shows_username_error(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("login-button").click()
        error = page.get_by_test_id("error-message")
        expect(error).to_be_visible()
        expect(error).to_have_text("请输入用户名")

    def test_empty_password_shows_error(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("login-button").click()
        error = page.get_by_test_id("error-message")
        expect(error).to_be_visible()
        expect(error).to_have_text("请输入密码")

    def test_nonexistent_user_shows_error(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("nobody")
        page.get_by_test_id("password-input").fill("wrong")
        page.get_by_test_id("login-button").click()
        error = page.get_by_test_id("error-message")
        expect(error).to_be_visible()
        expect(error).to_have_text("用户不存在")

    def test_wrong_password_shows_error(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("password-input").fill("wrongpass")
        page.get_by_test_id("login-button").click()
        error = page.get_by_test_id("error-message")
        expect(error).to_be_visible()
        expect(error).to_have_text("密码错误")

    def test_username_preserved_after_password_error(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("password-input").fill("wrongpass")
        page.get_by_test_id("login-button").click()
        expect(page.get_by_test_id("username-input")).to_have_value("admin")


class TestLoginSuccess:
    """Tests for successful login and redirect behavior."""

    def test_admin_login_redirects_to_system_user(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("password-input").fill("admin123")
        page.get_by_test_id("login-button").click()
        page.wait_for_url(re.compile(r"/system/user"))
        expect(page).to_have_title("用户管理")

    def test_user_login_redirects_to_system_user(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("user")
        page.get_by_test_id("password-input").fill("user123")
        page.get_by_test_id("login-button").click()
        page.wait_for_url(re.compile(r"/system/user"))
        expect(page).to_have_title("用户管理")

    def test_welcome_message_shows_username(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("password-input").fill("admin123")
        page.get_by_test_id("login-button").click()
        page.wait_for_url(re.compile(r"/system/user"))
        expect(page.get_by_test_id("welcome-user")).to_have_text("admin")

    def test_user_table_visible_after_login(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("password-input").fill("admin123")
        page.get_by_test_id("login-button").click()
        page.wait_for_url(re.compile(r"/system/user"))
        table = page.get_by_test_id("user-table")
        expect(table).to_be_visible()
        rows = table.locator("tbody tr")
        expect(rows).to_have_count(2)

    def test_page_title_on_system_user(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("password-input").fill("admin123")
        page.get_by_test_id("login-button").click()
        page.wait_for_url(re.compile(r"/system/user"))
        expect(page.get_by_test_id("page-title")).to_have_text("用户管理")


class TestAuthGuard:
    """Tests for authentication guard and redirect behavior."""

    def test_unauthenticated_access_redirects_to_login(self, page: Page, base_url: str):
        page.context.clear_cookies()
        page.goto(f"{base_url}/system/user")
        expect(page).to_have_url(re.compile(r"/login\?redirect="))
        expect(page).to_have_title("系统登录")

    def test_redirect_param_preserved_in_form(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        hidden = page.locator('input[name="redirect"]')
        expect(hidden).to_have_value("/system/user")


class TestLogout:
    """Tests for logout functionality."""

    def test_logout_clears_session(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("password-input").fill("admin123")
        page.get_by_test_id("login-button").click()
        page.wait_for_url(re.compile(r"/system/user"))

        page.get_by_test_id("logout-link").click()
        expect(page).to_have_title("系统登录")

    def test_after_logout_cannot_access_protected_page(self, page: Page, base_url: str):
        page.goto(f"{base_url}{LOGIN_URL}")
        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("password-input").fill("admin123")
        page.get_by_test_id("login-button").click()
        page.wait_for_url(re.compile(r"/system/user"))

        page.get_by_test_id("logout-link").click()
        page.goto(f"{base_url}/system/user")
        expect(page).to_have_url(re.compile(r"/login"))


class TestLoginFlow:
    """End-to-end flow tests."""

    def test_full_login_logout_cycle(self, page: Page, base_url: str):
        """Complete flow: visit protected page -> redirect to login -> login -> access page -> logout."""
        page.context.clear_cookies()

        page.goto(f"{base_url}/system/user")
        expect(page).to_have_url(re.compile(r"/login"))

        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("password-input").fill("admin123")
        page.get_by_test_id("login-button").click()
        page.wait_for_url(re.compile(r"/system/user"))
        expect(page.get_by_test_id("welcome-user")).to_have_text("admin")

        page.get_by_test_id("logout-link").click()
        expect(page).to_have_title("系统登录")

    def test_login_with_wrong_then_correct_password(self, page: Page, base_url: str):
        """User enters wrong password first, then corrects it."""
        page.goto(f"{base_url}{LOGIN_URL}")

        page.get_by_test_id("username-input").fill("admin")
        page.get_by_test_id("password-input").fill("wrong")
        page.get_by_test_id("login-button").click()
        expect(page.get_by_test_id("error-message")).to_be_visible()

        page.get_by_test_id("password-input").fill("admin123")
        page.get_by_test_id("login-button").click()
        page.wait_for_url(re.compile(r"/system/user"))
        expect(page).to_have_title("用户管理")
