package TestCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Pages.SampleForm;
import Util.ExtentList;

public class SampleFormTest extends BaseTest
{
	
	@Parameters("browser")
	@BeforeMethod
	
	public void initilise(String browser) throws Exception
	{
		setup(browser);
		
	}
	
	@AfterMethod
	public void endTest()
	{
		killBrowser();
	}
		
	@Test(dataProviderClass=Util.DataReader.class,dataProvider="dp")
	public void valueTest(String name,String last,String email,String pwd, String gender) throws Exception
	{
		try
		{
		
		HomePage home= new HomePage(driver);
		SampleForm sample = home.sampleForm();
		Util.UrlTimer.urlTimerWait("/registrationform.html");
				
		Util.ExtentUtil.type(sample.getFirstName(),name, "First Name");
		Util.ExtentUtil.type(sample.getLastName(), last, "Last Name");
		Util.ExtentUtil.type(sample.getEmail(), email, "Email");
		Util.ExtentUtil.type(sample.getPwd(), pwd, "Password");
		
		Util.ExtentUtil.click(sample.getSports(), "Sports checkbox");
		Util.ExtentUtil.click(sample.getTraveling(), "Travelling checkbox");
		Util.ExtentUtil.click(sample.getReading(), "Reading checkbox");
				
		Util.ExtentUtil.dropdown(sample.getGender(), gender, "Gender ");
		Util.ExtentUtil.grid(sample.getAbout(), "Just to test");
		Util.ExtentUtil.click(sample.getRegister(), "Register");
		
		WebElement msg1=driver.findElement(sample.getMessage());	
		String act=	msg1.getText();
		String exp = "User registered successfully!";
		Assert.assertEquals(act, exp,"String not matched");
		//driver.navigate().back();
		}
		catch (Throwable t)
		{
			log.error(t.getMessage());
			ExtentList.test.fail(t.getMessage());
			Assert.fail();
						
		}
		
	}

}
