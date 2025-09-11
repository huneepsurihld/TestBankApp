package Util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import Base.BaseTest;

public class Screenshot extends BaseTest
{
	static File file;
	static Date d= new Date();
	static String name=d.toString().replace(":", "").replace(" ", "")+".png";
	public static void snapshot(ITestResult result) throws IOException
	{
		try
		{
			
		if(driver!=null && ((RemoteWebDriver) driver).getSessionId() != null)
		{	
		String method=result.getMethod().getMethodName();
		file=new File("D:\\Document\\Default Location\\PageObjectBank\\test-output\\screenshots\\"+method+"_"+name);
		File snaphot=((RemoteWebDriver) BaseTest.driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(snaphot, file);
		}
		}
		catch(Throwable t)
		{
			ExtentList.test.fail(t.getMessage());
			log.info(t.getMessage());
			throw t;
		}
	}

}
