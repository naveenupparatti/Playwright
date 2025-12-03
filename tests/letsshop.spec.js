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
  const productPrice=page.locator(".card-body .text-muted").nth(0);
  let prodprice=await productPrice.textContent();
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

//   verify the correct email is entered in the email field
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

// extract the order number from success page
let orderNumber=page.locator(".em-spacer-1 label").nth(1);
let orderNum=await orderNumber.textContent();
console.log(orderNum);
let orderNumb=orderNum.split('|')[1];
orderNumb=orderNumb.trim();
console.log(orderNumb);

// Navigate to orders page
let orderSummaryPage=page.locator(".em-spacer-1 label").nth(0);
await orderSummaryPage.click();

// verify order is present in orders page
const orders=page.locator("tbody tr");
await orders.first().waitFor();
let ordersCount=await orders.count();
let found=false;
for(let i=0;i<ordersCount;i++)
  {
    let orderRowtext=await orders.nth(i).locator("th").textContent();
    console.log(orderRowtext);
    if(await orderNumb===orderRowtext)
    {
      found=true;
      await orders.nth(i).locator("button").first().click();
      break;
    }
  } 
  await expect(found).toBeTruthy();


  // validate order id on order summary page
  const orderIdOnSummary=page.locator(".col-text ").nth(0);
  let orderIdTextOnSummary= await orderIdOnSummary.textContent();
  await expect(orderIdTextOnSummary).toBe(orderNumb);

  //validate product name
  const productNameOnSummary=page.locator(".artwork-card-info .title");
  let productNameOnSummaryText=await productNameOnSummary.textContent();
  await expect(productNameOnSummaryText.trim()).toBe(productName);

  // validate price
  const priceOnSummary=page.locator(".artwork-card-info .info .price");
  let priceOnSummaryText=await priceOnSummary.textContent();
  await expect(priceOnSummaryText.trim()).toBe(prodprice);
  



});