package POBAbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameWorkProject2.SeleniumPOB.CartPage;
import SeleniumFrameWorkProject2.SeleniumPOB.OrdersPage;

public class  AbstractComponents {
	WebDriver driver;


	
	@FindBy(xpath="//button[contains(@routerlink,'/dashboard/myorders')]")
	WebElement ordersButton;
	
	public AbstractComponents(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElementToAppear(By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	
	public void waitForElementToDissappear(By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		  wait.until(ExpectedConditions.invisibilityOfElementLocated(FindBy));
	}
	
	public void WaitTillTheElementIsClickable(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public OrdersPage openOrdersPage()
	{
		WaitTillTheElementIsClickable(ordersButton);
		System.out.println("waiting for the button");
//		try(){
		ordersButton.click();
//		catch (Exception e) {
//		    e.printStackTrace();
		OrdersPage ordersPage=new OrdersPage(driver);
		return ordersPage;
		
	}
	
	

}
