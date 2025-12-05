const {test,expect,request}=require("@playwright/test");
const {ApiUtils}=require("./utils/ApiUtils");

const loginPayload={userEmail:"luffysingh@gmail.com",userPassword:"Luffy@12345"};
const orderPayload={orders:[{country:"India",productOrderedId:"68a961459320a140fe1ca57a"}]};

let response;
test.beforeAll("api test",async()=>
{
   const apiContext=await request.newContext();
   const apiUtils=new ApiUtils(apiContext,loginPayload);
   response=await apiUtils.getOrderId(orderPayload);

});

test("enter the correct login credentials",async ({page})=>
{ 
    await  page.addInitScript(value =>{
        window.localStorage.setItem('token',value);
     },response.token)

    // const email=page.locator("[type='email']");
    // const pass=page.locator("[type='password']");
    // const login=page.locator("#login");
    // const titles=page.locator(".card .card-body h5");
    // const emailid="luffysingh@gmail.com";
    await page.goto("https://rahulshettyacademy.com/client/");
      const productName="ZARA COAT 3";

    // Navigate to orders page
    let orderSummaryPage=page.locator("li .btn-custom").nth(1);
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
        if(await response.orderId===orderRowtext)
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
      await expect(orderIdTextOnSummary).toBe(response.orderId);

      //validate product name
      const productNameOnSummary=page.locator(".artwork-card-info .title");
      let productNameOnSummaryText=await productNameOnSummary.textContent();
      await expect(productNameOnSummaryText.trim()).toBe(productName);

    //   // validate price
    //   const priceOnSummary=page.locator(".artwork-card-info .info .price");
    //   let priceOnSummaryText=await priceOnSummary.textContent();
    //   await expect(priceOnSummaryText.trim()).toBe(prodprice);
      



});