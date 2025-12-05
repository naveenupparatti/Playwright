const {test,expect}=require("@playwright/test");

test(" selecting date in calender ",async ({page})=>
{
    const date="15";
    const monthNum="5";
    const year="2027";
   let expectedList=[monthNum,date,year];

   await page.goto("https://rahulshettyacademy.com/seleniumPractise/#/offers");
   await page.locator(".react-date-picker__button__icon").nth(1).click();
   await page.locator(".react-calendar__navigation__label__labelText--from").click();
   await page.locator(".react-calendar__navigation__label__labelText--from").click();
   await page.locator(".react-calendar__tile",{hasText:''+year+''}).click();
   await page.locator(".react-calendar__year-view__months__month").nth(Number(monthNum-1)).click();
   await page.locator("//abbr[text()='"+date+"']").click();

   for(let i=0;i<expectedList.length;i++)
   {
     let value=await page.locator(".react-date-picker__inputGroup__input").nth(i).inputValue();
     await expect(value).toEqual(expectedList[i]);
   }

});

test("validating dailogs and hovering action",async ({page})=>
{
  await  page.goto("https://rahulshettyacademy.com/AutomationPractice/");
  await page.pause();
  await page.locator("#mousehover").hover();
  await page.locator('[href="#top"]').click();
  page.on('dialog',dialog=>dialog.accept());
  await page.locator("#confirmbtn").click();


  

});

test(" validating frame",async ({page})=>
{
   await page.goto("https://rahulshettyacademy.com/AutomationPractice/");
   const framePage= await page.frameLocator("#courses-iframe");
   await framePage.locator('#name').fill("luffy");
});