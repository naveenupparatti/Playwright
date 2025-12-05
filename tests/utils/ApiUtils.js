class ApiUtils{

    constructor(apiContext,loginPayload)
    {
       this.apiContext=apiContext;
       this.loginPayload=loginPayload;
    }

    async getToken()
    {
            const loginResponse=await this.apiContext.post("https://rahulshettyacademy.com/api/ecom/auth/login",
            {
            data:this.loginPayload,
            })
           
            const loginJson=await loginResponse.json();
            let token=await loginJson.token;
            return token;
    }

    async getOrderId(orderPayload)
    {
        let response={};
        response.token=await this.getToken();
        const orderResponse =await this.apiContext.post("https://rahulshettyacademy.com/api/ecom/order/create-order",
            {
                data:orderPayload,
                headers:{
                    "authorization":response.token,
                    "content-type":"application/json"
                }
            }
        )
        
        const orderJson=await orderResponse.json();
        const orderId=await orderJson.orders[0];
        response.orderId=orderId;
        return response;
    }

    
}
module.exports={ApiUtils};
