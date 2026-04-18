# E2E Tests – Login Page

Automated end-to-end tests for `http://localhost/login?redirect=%2Fsystem%2Fuser`  
Written with [Playwright](https://playwright.dev/) and TypeScript.

## Prerequisites

- Node.js ≥ 18
- The target web application must be running at `http://localhost`

## Setup

```bash
cd e2e-tests
npm install
npx playwright install chromium
```

## Running tests

```bash
# Headless (CI-friendly)
npm test

# With browser visible
npm run test:headed

# View HTML report after a run
npm run test:report
```

When the application is not running on `http://localhost`, all tests are automatically **skipped** with a clear warning — the suite exits with code 0.

## Environment variables

| Variable | Description |
|---|---|
| `BASE_URL` | Override base URL (default: `http://localhost`) |
| `TEST_USERNAME` | Username for successful-login test |
| `TEST_PASSWORD` | Password for successful-login test |

Example:
```bash
TEST_USERNAME=admin TEST_PASSWORD=admin123 npm test
```

## Test cases

| Test | Description |
|---|---|
| renders the login page correctly | Page loads, at least one input is visible |
| shows validation error on empty submission | Empty submit stays on login or shows error |
| rejects invalid credentials | Wrong creds keep user on login page or show error |
| redirects to /system/user after successful login | Valid login navigates to `/system/user` (requires `TEST_USERNAME`/`TEST_PASSWORD`) |
| redirect query parameter is preserved on page load | URL still contains `/login` on arrival |
| login page is accessible (no JS errors on load) | Page body renders without critical JS errors |

## Configuration

See `playwright.config.ts` for full Playwright configuration (timeouts, retries, reporters, etc.).
