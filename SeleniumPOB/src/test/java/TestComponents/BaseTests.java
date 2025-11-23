package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameWorkProject2.SeleniumPOB.LandingPage;

public class BaseTests {
	
	public WebDriver driver;
	
	public WebDriver initializeDriver() throws IOException {
	
		Properties prop=new Properties();
		FileInputStream is=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Global\\resources\\GLobalData.properties");
		prop.load(is);
		
	
		String browserName=System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		System.setProperty("webDriver.chrome.driver","C:\\\\Users\\\\NUPPARAT\\\\eclipse-workspace\\\\chromedriver-win64\\\\chromedriver.exe");
		 driver=new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webDriver.gecko.driver","C:\\java\\geckodriver-v0.36.0-win64\\geckodriver.exe");
			 driver=new FirefoxDriver();
		}
//		else if(browserName.equalsIgnoreCase("edge"))
//		{
//			w
//			
//		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	public  LandingPage launchApplication() throws IOException
	{
		
		driver=initializeDriver();
		LandingPage lp=new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	public List<HashMap<String, String>> dataRader(String filePath) throws IOException
	{
//		covert json to String format
		 String jsonData=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		 
//		 covert String to hashMap
		 ObjectMapper mapper=new ObjectMapper();
		 List<HashMap<String,String>> data=mapper.readValue(jsonData,new TypeReference<List<HashMap<String,String>>>(){});
		 return data;
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return  System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}


}
