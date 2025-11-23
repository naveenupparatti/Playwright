package SeleniumFrameWorkProject2.SeleniumPOB;

import java.io.IOException;

import org.testng.annotations.Test;

import TestComponents.BaseTests;

public class LoginPageTest extends BaseTests{
	
	
//	correct id and pass
	

	@Test(groups= {"smoke"})
	public void correctIdAndPass() throws IOException
	{
		 LandingPage lp=launchApplication();
		 ProductCatalogue ProductCatalogue=lp.loginApplication("naveenbu18@gmail.com","devilFruit@123");
		 
	}
	
	@Test
	public void IncorrectIdAndPass() throws IOException
	{
		 LandingPage lp=launchApplication();
		 ProductCatalogue ProductCatalogue=lp.loginApplication("naveenb18@gmail.com","devlFruit@123");
		 
	}
	

}
