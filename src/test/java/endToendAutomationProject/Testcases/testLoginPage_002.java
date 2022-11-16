package endToendAutomationProject.Testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import endToendAutomationProject.PageObject.Loginpage;
import endToendAutomationProject.utility.XLutility;

public class testLoginPage_002 extends baseTest {

    @Test(dataProvider ="logindata")
	public void loginDDT(String userName, String passWord) throws InterruptedException, IOException
	{
    	Loginpage lp=new Loginpage(driver);
    	lp.setusername(userName);
    	log.info("User Name Enter");
    	lp.setpassword(passWord);
    	log.info("User Password Enter");
    	lp.loginclick();
    	Thread.sleep(3000);
    	
    	if(isAlertpresernt()==true)
    	{
    		getscreenshotfail(driver,"wrong credential");
    		driver.switchTo().alert().accept();
    		driver.switchTo().defaultContent();
    		Assert.assertTrue(false);
    		log.info("Login Test Failed");

    		
    	}else {
    		Assert.assertTrue(true);
    		log.info("Login Test passed");
    		Thread.sleep(3000);
    		lp.logout();
    		driver.switchTo().alert().accept();
    		driver.switchTo().defaultContent();
    	}
    		
	}
	
	public boolean isAlertpresernt()
	{
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	
	@DataProvider(name="logindata")
	 String[][] getdata() throws IOException
	{
		String path="C:\\Users\\gaura\\eclipse-workspace\\endToendAutomationProject\\datafiles\\Logindata.xlsx";
		
		int rowcount = XLutility.getRowCount(path, "Sheet1");
		int cellcount=XLutility.getcellCount(path,"Sheet1",1);
		String logindata[][]=new String[rowcount][cellcount];
		
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<cellcount;j++)
			{
				logindata[i-1][j]=XLutility.getcelldata(path, "Sheet1", i, j);
				
			}
		}
		return logindata;
	}
	
	
	
}
