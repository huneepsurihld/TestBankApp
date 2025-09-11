package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LifeTimeMembership;
import Util.ExtentList;

public class TestLifeTimeMembership extends BaseTest
{
	@BeforeTest
	@Parameters("browser")
	
	public void startup(String browser) throws Exception
	{
		setup(browser);
		
	}
	
	@AfterTest
	public void finish()
	{
		killBrowser();
	}
	
	
	@Test
	public void pageNavigationTest() throws Exception
	{
		try 
		{
		HomePage home= new HomePage(driver);
		LifeTimeMembership life = home.lifetimeMembership();
		String expectedURL=life.URL();
		Base.URLCheck.urlCheck("lifetime-membership-club", expectedURL);
		}
		catch(Exception e)
		{
			ExtentList.test.fail(e.getMessage());
			Assert.fail();
		}
	}
}
