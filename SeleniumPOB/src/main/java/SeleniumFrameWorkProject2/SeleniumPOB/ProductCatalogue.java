package SeleniumFrameWorkProject2.SeleniumPOB;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.By;

import POBAbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	
	By productsBy=By.cssSelector(".mb-3");
	By productNameFromList=By.cssSelector(".mb-3 b");
	By addToCartBy=By.cssSelector(".mb-3  .fa-shopping-cart");
	By toastConatinerBy=By.id("toast-container");
	By animationOverlay=By.cssSelector(".ng-animating");
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement clickOnCartButton;
	

	
   public List<WebElement>	getProductsList()
   {
	   waitForElementToAppear(productsBy);
	   return products;
	   
   }
	
   public  WebElement getProductByName(String prodName)
   {
	   WebElement prod= getProductsList().stream().filter(product->product.findElement(productNameFromList).getText().equalsIgnoreCase(prodName)).findFirst().orElse(null);
	   System.out.println(prod.getText());
	   return prod;
   }
   
   public  void addProductToCart(String prodName)
   {
	  WebElement prod=getProductByName(prodName);
	  prod.findElement(addToCartBy).click();
	  waitForElementToAppear(toastConatinerBy);
	  waitForElementToDissappear(animationOverlay);
	  
   }
   
   public CartPage openCartPage()
   {
	   
	   WaitTillTheElementIsClickable(clickOnCartButton);
	   System.out.println("cart button is visible");
	   clickOnCartButton.click();
	   CartPage  cartPage=new CartPage(driver);
	   return cartPage;
   }
   
   
   
   
   
   
 
  
	
	

}
