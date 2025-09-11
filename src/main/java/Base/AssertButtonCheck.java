package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Util.ExtentList;


public class AssertButtonCheck extends BaseTest

{
	
	public static void buttonAssert(By locator, WebDriver driver, int t)
	{
		if(!BaseTest.isAvailable(locator, driver, t))
		{
			
			log.error(locator.toString()+" not found");
			
			Assert.assertEquals(false, locator.toString()+" not found");
			
			
		}
		
	}

}
