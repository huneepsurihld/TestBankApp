package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Util.DriverManager;
import Util.ExtentList;

public class HomePageTest extends BaseTest
{
	
	@Parameters("browser")
	@BeforeMethod
	public void initialize(String browser) throws Exception
	{
		setup(browser);
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		killBrowser();
	}
	
	
@Test
	public void home() 
	{
		try
		{
		HomePage home=new HomePage(driver);
		home.home();
		String expectedURL = "https://www.way2automation.com/angularjs-protractor/banking/#/login";
		String actualURL=DriverManager.getDriver().getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL);
		
		}
		catch(Exception e)
		{
			ExtentList.test.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
	
		
@Test
	public void buttonsCheck() 
	{
			
		try 
		{
		
			HomePage home=new HomePage(driver);
			
			Util.Timer.wait(home.getCustLogin());	
			Base.AssertButtonCheck.buttonAssert(home.getCustLogin(), DriverManager.getDriver(), 10);
			Base.AssertButtonCheck.buttonAssert(home.getBankMngrLogin(), DriverManager.getDriver(), 10);
			Base.AssertButtonCheck.buttonAssert(home.getHome(), DriverManager.getDriver(), 10);
			
							
		}
		catch(Exception e)
		{
			log.info(e.getMessage());
			ExtentList.test.fail(e.getMessage());
			Assert.fail();
		}
		
	}
	@Test
	public void custLogin()
	{
		try 
		{
			HomePage home=new HomePage(driver);
			Util.Timer.wait(home.getCustLogin());
			home.custLogin();
			String value = HomePage.actualURL;
			Base.URLCheck.urlCheck("#/customer",value);
							
		}
		catch(Exception e)
		{
			log.info(e.getMessage());
			ExtentList.test.fail(e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	public void mngrLogin()
	{
		try 
		{			
			HomePage home=new HomePage(driver);
			home.bankMngrLogin();
			String value=home.actualURL;
			Base.URLCheck.urlCheck("#/manager",value);
		}
		catch(Exception e)
		{
			log.info(e.getMessage());
			ExtentList.test.fail(e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	public void lifeTimeMembership ()
	{
		try
		{
			HomePage home=new HomePage(driver);
			home.lifetimeMembership();
			String value = HomePage.actualURL;
			Base.URLCheck.urlCheck("lifetime-membership-club/", value);
					
		}
		catch (Throwable e)
		{
			log.info(e.getMessage());
			ExtentList.test.fail(e.getMessage());
			Assert.fail();
		}
	}
		
	
	
}
