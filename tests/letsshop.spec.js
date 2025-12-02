const {test}=require("@playwright/test");

test("enter the correct login credentials",async ({page})=>
{ 
    const email=page.locator("[type='email']");
    const pass=page.locator("[type='password']");
    const login=page.locator("#login");
    const titles=page.locator(".card .card-body h5");
    await page.goto("https://rahulshettyacademy.com/client/#/auth/login");
    await email.fill("luffysingh@gmail.com");
    await pass.fill("Luffy@12345");
    await login.click();

    // await page.waitForLoadState("networkidle");
    await titles.first().waitFor();
    console.log(await titles.allTextContents());


});