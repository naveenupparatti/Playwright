const {test,expect}=require("@playwright/test");

test("enter the correct login credentials",async ({page})=>
{ 
    const email=page.locator("[type='email']");
    const pass=page.locator("[type='password']");
    const login=page.locator("#login");
    const titles=page.locator(".card .card-body h5");
    const emailid="luffysingh@gmail.com";
    await page.goto("https://rahulshettyacademy.com/client/#/auth/login");
    await email.fill(emailid);
    await pass.fill("Luffy@12345");
    await login.click();

    // await page.waitForLoadState("networkidle");
    await titles.first().waitFor();
    console.log(await titles.allTextContents());

  const productName="ZARA COAT 3";
  let  proudcts=page.locator(".card-body");
    let count=await proudcts.count();
    //   click on add to cart button
    for(let i=0;i<count;i++)
    {
        if(await proudcts.nth(i).locator("b").textContent()===productName)
        {
            await proudcts.nth(i).locator("text= Add To Cart").click();
            break;
        }
    }

    
    //   click on cart button
    await page.locator("[routerlink='/dashboard/cart']").click();

    //   verify product is added in cart
    let cartProductName=page.locator(".infoWrap");
    await cartProductName.first().waitFor();
    let cartProdCount=await cartProductName.count();
    for(let i=0;i<cartProdCount;i++)
    {
    if(await cartProductName.nth(i).locator("h3").textContent()===productName)
    {
        await cartProductName.nth(i).locator("button:has-text('Buy Now')").click();
    }
    }

// payment page selecting country
  const dropdownn=page.locator("[placeholder='Select Country']");
  await dropdownn.pressSequentially("ind");
  let options=page.locator(".ta-item");
  await options.first().waitFor();
  const optCount=await options.count();
  for(let i=0;i<optCount;i++)
  {
    if(await options.nth(i).locator("span").textContent()===" India")
    {
        await options.nth(i).click();
        break;
    }
  }

//   verify the correct email is entered in the email fiedl
 const emailField=page.locator(".user__name input").nth(0);
 await expect(emailField).toHaveValue(emailid);

  //click on place order button
  const submitButton=page.locator(".action__submit ");
  await submitButton.click();

//   extract the success meassge to verify order is successfull
const successMessage=page.locator(".hero-primary");
let successText=await successMessage.innerText();
successText=successText.trim();
await expect(successMessage).toHaveText(" Thankyou for the order. ");

let orderNumber=page.locator(".em-spacer-1 label").nth(1);
let orderNum=await orderNumber.textContent();

let orderSummaryPage=page.locator(".em-spacer-1 label").nth(0);
await orderSummaryPage.click();




});