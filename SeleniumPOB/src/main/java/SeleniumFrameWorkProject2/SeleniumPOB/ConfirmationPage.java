package SeleniumFrameWorkProject2.SeleniumPOB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
     WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")	
	WebElement confimMessage;
	
	public String getConfirmMessage()
	{
		return confimMessage.getText();
		
	}
			

}
