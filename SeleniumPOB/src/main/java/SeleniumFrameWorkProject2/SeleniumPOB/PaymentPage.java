package SeleniumFrameWorkProject2.SeleniumPOB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import POBAbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents{
	WebDriver driver;
	
	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountryies;
	
	By selectFromList=By.cssSelector(".ta-results .ta-item:nth-of-type(2) span");
	
	
	
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	 
	public void selectCountry(String country)
	{
		selectCountryies.sendKeys(country);
		waitForElementToAppear((selectFromList));
		driver.findElement(selectFromList).click();
	}
	
	public ConfirmationPage goToConfirmationPage()
	{
		submitButton.click();
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
		
	}

}
