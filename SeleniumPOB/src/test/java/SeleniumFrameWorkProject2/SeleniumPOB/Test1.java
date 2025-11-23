package SeleniumFrameWorkProject2.SeleniumPOB;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import POBAbstractComponents.AbstractComponents;
import TestComponents.BaseTests;
import TestComponents.Retry;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
public class Test1 extends BaseTests{
	

	 String productToBuy="ZARA COAT 3";
	 String id="naveenbu18@gmail.com";
	 String pass="devilFruit@123";
	
	@Test(dataProvider = "dataContent")
	public  void orderZaraCoat(HashMap<String,String> input) throws IOException { 
//		Test1 Data 
		
		 String country="Ind";
		 LandingPage lp=launchApplication();
		 ProductCatalogue ProductCatalogue=lp.loginApplication(input.get("id"),input.get("pass"));
		List<WebElement> product=ProductCatalogue.getProductsList();
		ProductCatalogue.addProductToCart(input.get("productToBuy"));
		CartPage cartPage=ProductCatalogue.openCartPage();
	    Assert.assertTrue(cartPage.verifyProductDisplay(input.get("productToBuy")));
		PaymentPage paymentPage=cartPage.goToPaymentPage();
		paymentPage.selectCountry(country);
		ConfirmationPage confirmationPage=paymentPage.goToConfirmationPage();
		Assert.assertTrue(confirmationPage.getConfirmMessage().equals("THANKYOU  THE ORDER."));		
	}
	
	@Test(dependsOnMethods= {"orderZaraCoat"})
	public void orderHistoryTest() throws IOException
	{
     LandingPage lp=launchApplication();
     ProductCatalogue ProductCatalogue=lp.loginApplication(id,pass);
     OrdersPage ordersPage=ProductCatalogue.openOrdersPage();
     Assert.assertTrue(ordersPage.verifyOrderDisplay(productToBuy));
     
	}

	@DataProvider
	public Object[][] dataContent() throws IOException
{
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("productToBuy", "ZARA COAT 3");
//		map.put("id", "naveenbu18@gmail.com");
//		map.put("pass", "devilFruit@123");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("productToBuy", "ADIDAS ORIGINAL");
//		map1.put("id", "naveenbu18@gmail.com");
//		map1.put("pass", "devilFruit@123");
	    
	
	    List<HashMap<String,String>> data =dataRader(System.getProperty("user.dir")+"\\src\\test\\java\\DataSet\\OrderEntry.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
//		
	}
	
	
	
}

	
	
	
	

