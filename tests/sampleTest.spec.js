
const {test, expect}=require('@playwright/test');


test('first playwright test with browser context',async ({browser})=>
{
    const context=await browser.newContext();
    const page=await context.newPage();
     await page.goto("https://rahulshettyacademy.com/angularpractice/");
     console.log(await page.title());
     

}

);


test("playwright test with direct page ",async ({page})=>
{

 await page.goto("https://www.google.com/");
 let title=await page.title();
 console.log('tittle is '+title);
 await expect(page).toHaveTitle("Google");
}


);
