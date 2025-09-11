package TestCases;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseTest;
import Pages.BankManagerLogin;
import Pages.HomePage;
import Util.DriverManager;
import Util.ExtentList;
import Util.ExtentUtil;

public class BankManagerTest extends BaseTest
{
	public HomePage home;
	public BankManagerLogin mngr;
	SoftAssert softAssert=new SoftAssert();
	
	@Parameters("browser")
	@BeforeMethod
	
	public void startup(String browser) throws Exception
	{
		setup(browser);
		
	}

	@AfterMethod
	public void finish()
	{
		killBrowser();
	}
	
	private BankManagerLogin addCust() 
	{
		
		home=new HomePage(driver);
		Util.Timer.wait(home.getBankMngrLogin());
		mngr=home.bankMngrLogin();
		Util.Timer.wait(mngr.getAddCust());
		ExtentUtil.click(mngr.getAddCust(), "Add Customer");
		Util.Timer.wait(mngr.getfName());
		return mngr;
	}
	
	private BankManagerLogin getOpenAcc() 
	{
		home=new HomePage(driver);
		Util.Timer.wait(home.getBankMngrLogin());
		mngr=home.bankMngrLogin();
		Util.Timer.wait(mngr.getOpenAcc());
		ExtentUtil.click(mngr.getOpenAcc(), "Open Account");
		Util.Timer.wait(mngr.getCustDrp());
		return mngr;
	}
	
	private BankManagerLogin getCustDetail() 
	{
		home=new HomePage(driver);
		Util.Timer.wait(home.getBankMngrLogin());
		mngr=home.bankMngrLogin();
		Util.Timer.wait(mngr.getCustomer());
		ExtentUtil.click(mngr.getCustomer(), "Show Customer Button");
		Util.Timer.wait(mngr.getSearch());
		Assert.assertTrue(isAvailable(mngr.getSearch(),driver,5));
		Util.Timer.wait(mngr.getTable());
		return mngr;
		
	}
	

	@Test(priority=1, dataProviderClass=Util.DataReader.class,dataProvider="dp")
	public void openAccount( String name) throws Exception
	{	
		try
		{
		
		mngr = getOpenAcc();
		WebElement cust=DriverManager.getDriver().findElement(mngr.getCustDrp());
		Select select= new Select(cust);
		WebElement curr= DriverManager.getDriver().findElement(mngr.getCurrencyDrp());
		Select selectC=new Select(curr);
		List<WebElement> currency=selectC.getOptions();
		select.selectByVisibleText(name);
				
		Random random=new Random();
		int randomIndex=1+random.nextInt(currency.size()-1);
		selectC.selectByIndex(randomIndex);
		Util.Timer.wait(mngr.getProcess());
		ExtentUtil.click(mngr.getProcess(),"Process Button");
		Util.Timer.waitAlert();
		Alert alert= DriverManager.getDriver().switchTo().alert();
		String actualMsg=alert.getText();
		String expMsg=mngr.getOpenAccMsg();
		Assert.assertTrue(actualMsg.startsWith(expMsg));
		}
		catch(Exception e)
		{
			ExtentList.test.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
	
	

	
@Test
	public void placeHolderTest() 
	{
		try
		{
			mngr =addCust();			
			String firstName = driver.findElement(mngr.getfName()).getDomAttribute("placeholder");
			String lastName = driver.findElement(mngr.getlName()).getDomAttribute("placeholder");
			String code= driver.findElement(mngr.getCode()).getDomAttribute("placeholder");
			
		 	softAssert.assertEquals(firstName, mngr.fname(),"First name Placeholder not matched");
			softAssert.assertEquals(lastName, mngr.lname(),"Last name Placeholder not matched");
			softAssert.assertEquals(code,mngr.code(),"Code placeholder text not matched");
			softAssert.assertAll();
		 			 	
		}
		catch(Exception e)
		{
			ExtentList.test.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}
	
	
	}
	@Test(dataProviderClass=Util.DataReader.class,dataProvider="dp")

	public void addCust(String fName, String lName, String code) throws Exception
	{
		
		mngr=addCust();
		ExtentUtil.type(mngr.getfName(), fName, "First Name");
		ExtentUtil.type(mngr.getlName(), lName, "Last Name");
		ExtentUtil.type(mngr.getCode(), code, "Code");
		ExtentUtil.click(mngr.getAddCustButton(), "Add Customer Submit Button");
		Util.Timer.waitAlert();
		String successMsg=mngr.getAlertMsg();
		Alert alert = driver.switchTo().alert();
		String actualMsg=alert.getText();
		Assert.assertTrue(actualMsg.startsWith(successMsg)); 
		alert.accept();
		
	}
	
	
	

@Test(dataProviderClass=Util.DataReader.class,dataProvider="dp")
	public void custDetails(String header) throws Exception
	{
		try
		{
			mngr= getCustDetail();
			List <WebElement> headerElements=DriverManager.getDriver().findElements(mngr.getTable());
			String headings[]=mngr.getHeaderNames();
			Assert.assertEquals(headerElements.size(), headings.length, "Header Count Mismatched..");
			for (int i=0;i<headerElements.size();i++)
			{
				Assert.assertEquals(headerElements.get(i).getText().trim(),headings[i],"Header names not matched...");
			}
		}
		
		catch(Exception e)
		{
			ExtentList.test.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}
		
	}
	
	@Test
	public void checkDeleteButton() throws Exception
	{
		try {
				
				mngr = getCustDetail();
				List <WebElement> rows=DriverManager.getDriver().findElements(mngr.getTableRows());
				for (int i=0;i<rows.size();i++)
				{
					By cell=By.xpath(mngr.getCell(i+1));					
					Boolean available = isAvailable(cell, DriverManager.getDriver(), 8);
					Assert.assertTrue(available,"Delete button not available");
				}			
			}
		catch(Exception e)
		{
			ExtentList.test.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}
		
	}
	
	
	@Test
	public void deleteTest() throws Exception
	{
	try {	
		
		mngr = getCustDetail();		
		ExtentUtil.click(mngr.getCustomer(), "Show Customer Button");
		Util.Timer.wait(mngr.getTable());
		List <WebElement> rowsBefore=driver.findElements(mngr.getTableRows());
		int beforeDelete= rowsBefore.size();
		ExtentUtil.click(mngr.getDelete(), "Delete");
		Util.Timer.wait(mngr.getTable());
		List <WebElement> rowsAfter= driver.findElements(mngr.getTableRows());
		int afterDelete = rowsAfter.size();
		Assert.assertEquals(beforeDelete, afterDelete+1,"Row did not gets deleted");
		}
	catch(Exception e)
	{
		ExtentList.test.fail(e.getMessage());
		Assert.fail(e.getMessage());
	}
	}
	
}
