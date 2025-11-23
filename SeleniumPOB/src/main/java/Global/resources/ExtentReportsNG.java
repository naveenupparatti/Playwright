package Global.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	
	public ExtentReports getReportObject()
	{
	  String path=System.getProperty("user.dir")+"//reports//index.html";
	  ExtentSparkReporter reporter=new ExtentSparkReporter(path);
	  reporter.config().setReportName("Web AUtomation Results");
	  reporter.config().setDocumentTitle("Test Results");
	  
	  ExtentReports extent=new ExtentReports();
	  extent.attachReporter(reporter);
	  extent.setSystemInfo("Tester","Naveen");
	  return extent;
	}
	
	
	
	
	
	
	

}
