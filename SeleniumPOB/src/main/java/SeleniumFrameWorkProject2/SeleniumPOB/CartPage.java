package SeleniumFrameWorkProject2.SeleniumPOB;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import POBAbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartWrap h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//li[@class=\"totalRow\"][3]//button")
	WebElement checkOut;
	
	
	
	public boolean  verifyProductDisplay(String productToBuy)
	{
		boolean  matchProd=cartProducts.stream().anyMatch(produc->produc.getText().equalsIgnoreCase(productToBuy));
		 return matchProd;
	}
	
	public PaymentPage goToPaymentPage()
	{
		checkOut.click();
		waitForElementToAppear(By.cssSelector("input[placeholder='Select Country']"));
		PaymentPage paymentPage=new PaymentPage(driver);
		return paymentPage;
		
	}
	public void click()
	{
		System.out.println("click");
	}
	
	public void sendKeys()
	{
		System.out.println("send");
	}

}
