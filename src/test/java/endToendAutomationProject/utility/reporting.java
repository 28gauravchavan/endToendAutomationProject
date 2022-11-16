package endToendAutomationProject.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class reporting  extends TestListenerAdapter {

	
	ExtentHtmlReporter htmlreport;
	ExtentReports extent;
	ExtentTest extTest;
	
	public void onStart(ITestContext context) {
		String timeStamp= new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Date());
		String reportName="Test-Report"+timeStamp+".html";
		
	//	htmlreport=new ExtentHtmlReporter("C:\\Users\\gaura\\eclipse-workspace\\endToendAutomationProject\\test-output\\"+reportName);
	//	htmlreport.loadXMLConfig("C:\\Users\\gaura\\eclipse-workspace\\endToendAutomationProject\\extent-config.xml");

		
		htmlreport=new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtendReport/"+reportName);
     	htmlreport.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent=new ExtentReports();
		extent.attachReporter(htmlreport);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environement","QA");
		extent.setSystemInfo("user","Gaurav");
		
		htmlreport.config().setDocumentTitle("InetBanking Test Project");
		htmlreport.config().setReportName("Functional testing");
		htmlreport.config().setTestViewChartLocation(ChartLocation.TOP);
	    htmlreport.config().setTheme(Theme.DARK);

	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}


	public void onTestSuccess(ITestResult result) {
		extTest=extent.createTest(result.getName());
		extTest.log(Status.PASS,MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		
	}

	public void onTestFailure(ITestResult result) {
		extTest=extent.createTest(result.getName());
		extTest.log(Status.FAIL,MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		String screenshot=System.getProperty("user.dir")+"/Screenshot/"+result.getName()+".png";
		File f=new File(screenshot);
		
		if(f.exists())
		{
			try {
				extTest.fail("screenshot is below:- "+extTest.addScreenCaptureFromPath(screenshot));
				System.out.println("screen shot by auto failed");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	public void onTestSkipped(ITestResult result) {
		extTest=extent.createTest(result.getName());
		extTest.log(Status.SKIP,MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
		
		
	}



	
	
}
