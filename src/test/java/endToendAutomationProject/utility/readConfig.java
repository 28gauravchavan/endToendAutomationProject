package endToendAutomationProject.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class readConfig {

	Properties pro;
	public readConfig()
	{
	
	File path=new File("./Configuration/config.properties");
	try {
		FileInputStream fis=new FileInputStream(path);
	    pro=new Properties();
		pro.load(fis);
	
	    }
	catch(Exception e){
		System.out.println("Exception is: "+e);
	}
   }
	
  public String getApplicationUrl()
  {
	  
	  String baseurl=pro.getProperty("url");
	  return baseurl;	  
  }
  
  public String getusername()
  {
	  
	  String username=pro.getProperty("userName");
	  return username;	  
  }
  public String getpassword()
  {
	  
	  String password=pro.getProperty("passWord");
	  return password;	  
  }

}
