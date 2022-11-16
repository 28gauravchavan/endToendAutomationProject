package endToendAutomationProject.Testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.LogManager;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.utils.FileUtil;

import endToendAutomationProject.utility.readConfig;
import io.github.bonigarcia.wdm.WebDriverManager;



public class baseTest {

	
	readConfig readconfig=new readConfig();
	public WebDriver driver;
	public String url=readconfig.getApplicationUrl();
	public String userName=readconfig.getusername();
	public String passWord=readconfig.getpassword();
	public Logger log;
	
	@Parameters({"browser"})
	@BeforeClass
	public void setup(String br)
	{
		 
			//WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver();
			
			
			 log=Logger.getLogger("baseTest");
			PropertyConfigurator.configure("log4j.properties");
			
			if(br.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			
			}
			else if(br.equals("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			else if(br.equals("IE"))
			{
				WebDriverManager.iedriver().setup();
				driver=new InternetExplorerDriver();
			}else if(br.equals("safari"))
			{
				WebDriverManager.safaridriver().setup();
				driver=new SafariDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(url);
			driver.manage().window().maximize();
		
	}
	
	
	
	public void getscreenshotfail(WebDriver driver,String tname) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File scr = ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/Screenshot/"+tname+".png");
		FileUtils.copyFile(scr, target);
		System.out.println("screen shot is taken");
		
	}
	
	
	
	
	
	
	@AfterClass
	public void treardown()
	{
		driver.quit();
	}
	
	
	
	
	

}
