package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;//UI of the report
	public ExtentReports extent;//populate common info on the report
	public ExtentTest test;//creating test case entries in the report and update status of the test report
	
	String repName;
	
	public void onStart(ITestContext Context)
	{
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date()); //TimeStamp	
		repName="Test-Report-"+timestamp+".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);  //speciy location o the report
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); //Title of the Report
		sparkReporter.config().setReportName("Pet Store Users API"); //Name of the Report
		sparkReporter.config().setTheme(Theme.DARK); //Theme of the report
	
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Browser Name", "Google Chrome");
		
	}
 
	 public void onTestSuccess(ITestResult result)
	 {
		 test = extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.createNode(result.getName());
		 test.log(Status.PASS, "Test case PASSED is: "+ result.getName());
		 
	 }
	 
	 public void onTestFailure(ITestResult result) 
	 {
		 test = extent.createTest(result.getName());
		 test.createNode(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.FAIL, "Test case FAILED is: "+ result.getName());
		 test.log(Status.FAIL, "Test case FAILED  cause is: "+ result.getThrowable().getMessage());
	 }

	 public void onTestSkipped(ITestResult result) 
	 {
		 test = extent.createTest(result.getName());
		 test.createNode(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, "Test case SKIPPED is: "+ result.getName());
		 test.log(Status.SKIP, "Test case SKIPPED  cause is: "+ result.getThrowable().getMessage());
	 }
 
	 public void onFinish(ITestContext context) 
	 {
		   extent.flush();
	 }

}
