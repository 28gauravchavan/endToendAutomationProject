package endToendAutomationProject.Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import endToendAutomationProject.PageObject.Loginpage;

public class testLoginPage_001 extends baseTest {
	Loginpage loginpage;
	@Test()
	public void loginaccount()
	{
		
		 loginpage=new Loginpage(driver);
		loginpage.setusername(userName);
		loginpage.setpassword(passWord);
		loginpage.loginclick();
		log.info("url is open");
		String expected="GTPL Bank Manager HomePage";
		Assert.assertEquals(driver.getTitle(), expected);
				
	}
	
	
	
	
	@Test
	public void newcustlink()
	{
       boolean s= loginpage.newcustomer();
       log.info("new customer link open-");
       System.out.println(s);
	}
	
	
}
