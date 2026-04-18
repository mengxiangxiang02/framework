import { request } from '@playwright/test';

const BASE_URL = 'http://localhost';

async function globalSetup() {
  const context = await request.newContext({ baseURL: BASE_URL });
  try {
    await context.get('/login', { timeout: 5_000 });
    console.log(`✓ Server is reachable at ${BASE_URL}`);
  } catch {
    console.warn(
      `⚠  Server not reachable at ${BASE_URL}. ` +
        'Make sure the application is running before executing these tests. ' +
        'Tests will be skipped automatically.'
    );
    process.env.SERVER_UNAVAILABLE = 'true';
  } finally {
    await context.dispose();
  }
}

export default globalSetup;
