const {test}=require('@playwright/test');

test("angular page from validation ",async ({page})=>
{
    await page.goto("https://rahulshettyacademy.com/angularpractice/");
    let icreamButton=await page.getByLabel("Check me out if you Love IceCreams!").isChecked();
    if(icreamButton===false)
    {
        await page.getByLabel("Check me out if you Love IceCreams!").click();
    }
    await page.getByLabel("Student").click();

    await page.getByText('Shop').click();
    await page.locator(".row .mb-3").filter({hasText:"Nokia Edge"}).getByRole('button').click();
});

