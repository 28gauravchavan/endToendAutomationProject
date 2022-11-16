package endToendAutomationProject.PageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	
	 WebDriver driver;
	
	
	
	 public   Loginpage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="[name=\"uid\"]")
	WebElement usernamebox;
	
	@FindBy(css="[name=\"password\"]")
	WebElement passwordbox;
	
	
	@FindBy(name="btnLogin")
	WebElement loginbutton;
	
	@FindBy(name="btnReset")
	WebElement resetbutton;
	
	
	@FindBy(xpath="//a[contains(text(),'Manager')]/following::a[contains(text(),'New Customer')]")
	WebElement newcustomerlink;
	
	@FindBy(xpath="//a[normalize-space()='Log out']")
	WebElement logout;
	
	
	public void setusername(String userName)
	{
		usernamebox.sendKeys(userName);
	}
	public void setpassword(String pass)
	{
		passwordbox.sendKeys(pass);
	}
	
	public void loginclick()
	{
	  loginbutton.click();
	}
	public void logout()
	{
		logout.click();
	}

	public void resetclick()
	{
		resetbutton.click();
	}
	
	public boolean newcustomer()
	{
		boolean dis=newcustomerlink.isDisplayed();
		return dis;
	}
	
	
	
	public void ByVisibleElement(WebElement locator) 
	{
      
        JavascriptExecutor js = (JavascriptExecutor) driver;	
        WebElement element = locator;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
}
