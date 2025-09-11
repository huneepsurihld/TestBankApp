package Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Base.BaseTest;

public class ExtentUtil extends BaseTest

{
	public static void click(By locator, String fieldName)
	{
		try{
			BaseTest.driver.findElement(locator).click();
		
			BaseTest.log.info(fieldName+" clicked using locator: "+locator);
			ExtentList.test.info("Clicked on "+fieldName);
		}
		catch(Throwable e)
		{
			log.info("Unable to find "+fieldName+" Exception is "+e.getMessage());
			ExtentList.test.fail("Unable to find "+fieldName+" Exception is "+e.getMessage());
			Assert.fail(e.getMessage());
			throw e;
		}
	}
	

	public static void type(By locator, String value, String fieldName)
	{
		try 
		{
		BaseTest.driver.findElement(locator).sendKeys(value);
		log.info(value+" entered in "+fieldName);
		ExtentList.test.info(value+" entered in "+fieldName);
		}
		catch (Throwable t)
		{
			log.error("Unable to type "+value+" in "+fieldName+" Exception is: "+t.getMessage());
			ExtentList.test.fail("Unable to type "+value+" in "+fieldName+".The Exception is: "+t.getMessage());
			Assert.fail(t.getMessage());
			throw t;
		}
		
	}
	
	public static void dropdown(By locator, String value,String fieldName)
	{
		try
		{
			WebElement drpdwn = driver.findElement(locator);
			Select select = new Select(drpdwn);
			select.selectByValue(value);
			log.info("Dropdown "+fieldName+" is selected, with value "+value);
			ExtentList.test.info("Dropdown "+fieldName+" is selected, with value "+value);
						
		}
		catch (Throwable t)
		{
			log.error("Unable to found "+fieldName+" with value "+value+ ". The exception is: "+t.getMessage());
			ExtentList.test.fail("Unable to found "+fieldName+" with value "+value+ ". The exception is: "+t.getMessage());
			Assert.fail(t.getMessage());
			throw t;
		}
	}
		
	public static void checkBox(By locator, String fieldName)
	{
		try
		{
			WebElement chkbx = driver.findElement(locator);
			if (!chkbx.isSelected())
			{
				chkbx.click();
				log.info("Checkbox "+fieldName+" is selected");
				ExtentList.test.info("Checkbox "+fieldName+" is selected");
			}
			
		}
		
		catch (Throwable t)
		{
			log.error("Unable to found "+fieldName+". The exception is: "+t.getMessage());
			ExtentList.test.fail("Unable to found "+fieldName+". The exception is: "+t.getMessage());
			Assert.fail(t.getMessage());
			throw t;
		}
		
		
	}
		
	public static void grid(By locator, String value)
	{
		try
		{
		driver.findElement(locator).sendKeys(value);
		log.info(value+" values are added in Grid"+locator);
		ExtentList.test.info(value+" values are added in Grid"+locator);
		}
		catch (Throwable t)
		{
			log.error("Unable to found grid"+locator+". The exception is: "+t.getMessage());
			ExtentList.test.fail("Unable to found grid"+locator+". The exception is: "+t.getMessage());
			throw t;
		}
		
	}
	
	
}
