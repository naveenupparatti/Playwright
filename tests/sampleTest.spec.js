
const {test, expect}=require('@playwright/test');


test('first playwright test with browser context',async ({browser})=>
{
    const context=await browser.newContext();
    const page=await context.newPage();
     await page.goto("https://rahulshettyacademy.com/angularpractice/");
     console.log(await page.title());
      await page.locator("input.ng-invalid").nth(0).fill("Naveen");
     await page.locator("[name='email']").fill("naveenupp00@gmail.com");
     await page.locator("#exampleInputPassword1").fill("8660@Nav");
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

test('wrong login credential form validation',async ({page})=>
{
    const username=page.locator("#username");
    const password=page.locator("#password");
    const submit=page.locator(".btn-md");
    await page.goto("https://rahulshettyacademy.com/loginpagePractise/");
    await username.fill("naveenbu18@gmail.com");
    await password.fill("7qeqerqe");
    await submit.click();
    let errorMessage=await page.locator("[style*='block']").textContent();
    console.log( errorMessage);
    await expect(page.locator("[style*='block']")).toContainText("Incorrect");
    await username.fill("rahulshettyacademy ");
    await password.fill("learning");
    const dropdown=await page.locator("select.form-control");
    await dropdown.selectOption({index:2});
    // await page.pause();
    await page.locator(".customradio").nth(1).click();
    await page.locator("#okayBtn").click();
     await page.pause();
    await expect(page.locator(".customradio").nth(1)).toBeChecked();


    await submit.click();
});


test.only("extracting the text from new window",async ({browser})=>
{
    const context=await browser.newContext();
    const page =await context.newPage();
    await page.goto("https://rahulshettyacademy.com/loginpagePractise/");
    const newPageLink=page.locator("[href*='documents-request']");
    const [newPage]=await Promise.all([
    context.waitForEvent('page'),
     newPageLink.click()]);
    const text=await newPage.locator(".red").textContent();
    console.log(text);
});