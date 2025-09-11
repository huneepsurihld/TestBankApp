package TestCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CustomerLogin;
import Pages.HomePage;
import Util.DriverManager;
import Util.ExtentList;
import Util.ExtentUtil;



public class CustLoginTest extends BaseTest
{
	
	public HomePage home;
	public CustomerLogin cust;
			
	@BeforeMethod
	@Parameters("browser")
	public void startup(String browser) throws Exception
	{
		setup(browser);
		
	}
	
	@AfterMethod
	public void finish()
	{
		killBrowser();
	}
	
	private Select transactionPageSetup(String name) throws Exception
	{
		Select select=pageSetup();
		CustomerLogin cust=new CustomerLogin(driver);
		select.selectByVisibleText(name);
		Util.Timer.wait(cust.getLogin());
		ExtentUtil.click(cust.getLogin(), "Login");
		Util.Timer.wait(cust.getAccNumDrp());
		WebElement accNums=DriverManager.getDriver().findElement(cust.getAccNumDrp());
		return new Select(accNums);
		
	}
	
	private Select pageSetup() throws Exception
	{
		home= new HomePage(driver);
		Util.Timer.wait(home.getCustLogin());
		cust=home.custLogin();
		Util.Timer.wait(cust.getnameDrp());
		WebElement drpdown= driver.findElement(cust.getnameDrp());
		return new Select(drpdown);
		}
	
	private CustomerLogin getCust() 
	{
		home = new HomePage(driver);
		Util.Timer.wait(home.getCustLogin());
		cust=home.custLogin();
		Util.UrlTimer.urlTimerWait("#/customer");
		return cust;
	}
	
	
@Test(priority=1)
	public void checkCustomerButton() 
	{
		try
		{
			cust=getCust();
			String expected = HomePage.actualURL;
			String actual = driver.getCurrentUrl();
			Assert.assertEquals(actual, expected,"URL not matached");
			
		}
		catch(Exception t)
		{
			ExtentList.test.fail(t.getMessage());
			log.info(t.getMessage());
			throw t;
		}
	
	}
	
@Test(priority=2)
	public void drpDownSizeTest() throws Exception
	{
		try 
		{
		cust=getCust();		
		Util.Timer.wait(cust.getnameDrp());
		WebElement drp=driver.findElement(cust.getnameDrp());
		Select select = new Select(drp);
		List<WebElement>drpValues=select.getOptions();		
		int actual=drpValues.size();
		int expected = 6;
		Assert.assertEquals(actual, expected,"Numbers in dropdown not matched");
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			ExtentList.test.fail(e.getMessage());
			throw e;
		}
		
	}
	
	
@Test(priority=3)
	public void drpDownNamesTest () throws Exception
	
	{
		try 
		{
		cust=getCust();
		Util.Timer.wait(cust.getnameDrp());
		WebElement drp= driver.findElement(cust.getnameDrp());
		Select select = new Select(drp);
		List <WebElement> drpValues=select.getOptions();
		List <String> actualValues = new ArrayList<>();
		for (WebElement element:drpValues)
		{
			actualValues.add(element.getText());
		}
		
		String[] expectedValues=cust.getname();
		Assert.assertEquals(actualValues,Arrays.asList(expectedValues) ,"All names do not match");
		}
		catch (Exception e) 
		{
			ExtentList.test.fail(e.getMessage());
			log.error(e.getMessage());
			throw e;
		}
	}
	
	//------------Name Selection Test Case------------------------------------------------------------------
	

@Test
public void drpdownNameSelectionTest () throws Exception
{
	try
	{
		Select select= pageSetup();
		cust=new CustomerLogin(driver);
		List <WebElement> names = select.getOptions();
		for (int i=1;i<names.size();i++)
		{
			select.selectByIndex(i);
			String name = select.getFirstSelectedOption().getText();
			Util.Timer.wait(cust.getLogin());
			ExtentUtil.click(cust.getLogin(), "Login");
			Util.Timer.wait(cust.getHeader());
			String headerName = driver.findElement(cust.getHeader()).getText();
			Assert.assertEquals(headerName, name,"Drppdown selected value is not matched with header name");
			driver.navigate().back();
			Util.Timer.wait(cust.getnameDrp());
			WebElement drpdown=driver.findElement(cust.getnameDrp());
			select=new Select(drpdown);
		}
	}
	catch(Exception e)
	{
		ExtentList.test.fail(e.getMessage());
		log.error(e.getMessage());
		Assert.fail();
	}
	}

//---------------------After Login Correct Values Selection Test-----------------------------

@Test(dataProviderClass=Util.DataReader.class,dataProvider="dp")
public void afterLoginPageTest(String name) throws Exception
{
	try{
		
	Select select =pageSetup();
	ExtentList.test.assignAuthor("Honey");
	cust = new CustomerLogin(driver);
	select.selectByContainsVisibleText(name);
	ExtentUtil.click(cust.getLogin(), "Login");
	Util.Timer.wait(cust.getAccNumDrp());
	WebElement accDrpDown = driver.findElement(cust.getAccNumDrp());
	Select accSelect = new Select(accDrpDown);
	List <WebElement> accNums=accSelect.getOptions();
	for (int i=0;i<accNums.size();i++)
	{
		accSelect.selectByIndex(i);
		String acc=accSelect.getFirstSelectedOption().getText();
		WebElement actualNum=driver.findElement(cust.getAccNum());
		String actual=actualNum.getText();
		Assert.assertEquals(actual, acc,"Selected Dropdown value is not matched with Account number results");
		WebElement bal = driver.findElement(cust.getBal());
		Assert.assertTrue(bal.isDisplayed(),"Balance Not Displayed");
		WebElement currency=driver.findElement(cust.getCurrency());
		Assert.assertTrue(currency.isDisplayed(),"Currency Not Displayed");
		}
	}
	catch(Exception e)
	{
		ExtentList.test.fail(e.getMessage());
		log.error(e.getMessage());
		Assert.fail(e.getMessage());
	}
}

//-------------------------------------Transaction Page Back Test-------------------------------------------

@Test(dataProviderClass=Util.DataReader.class,dataProvider="dp")
public void TransactionBackButttonTest(String name) 
	{
	try{
		Select accSelects= transactionPageSetup(name);
		cust=new CustomerLogin(driver);
		List <WebElement> num=accSelects.getOptions();
			for (int i=0;i<num.size();i++)
				{
				WebElement accNum=driver.findElement(cust.getAccNumDrp());
				Select accSelect=new Select(accNum);
				accSelect.selectByIndex(i);
				Util.Timer.wait(cust.getTrans());
				ExtentUtil.click((cust.getTrans()), "Transaction Button");
				Util.Timer.wait(cust.getBack());
				ExtentUtil.click(cust.getBack(), "Back");
				Util.Timer.wait(cust.getHeader());
				String holderName=driver.findElement(cust.getHeader()).getText();
				Assert.assertEquals(holderName, name,"Back Navigation Failed");
				}
		}
		catch(Exception e)
		{
			ExtentList.test.fail(e.getMessage());
			log.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

//--------------------------Transaction Page Table Test -------------------------------

@Test(dataProviderClass=Util.DataReader.class,dataProvider="dp")
public void transactionTableTest( String name) throws Exception
{
	try {
		Select accSelect = transactionPageSetup(name);
		cust=new CustomerLogin(driver);
		List <WebElement> values=accSelect.getOptions();
	
		for (int i=0;i<values.size();i++)
		{
			Select accSelects = new Select(driver.findElement(cust.getAccNumDrp()));
			accSelects.selectByIndex(i);
			Util.Timer.wait(cust.getTrans());
			ExtentUtil.click(cust.getTrans(), "Transaction");
			Util.Timer.wait(cust.getTransTable());
			List <WebElement> table=driver.findElements(cust.getTransTable());
			Assert.assertTrue(!table.isEmpty(),"Table is Empty");
			ExtentUtil.click(cust.getBack(), "Back");
		}
	}
		catch(Exception e)
		{
			ExtentList.test.fail(e.getMessage());
			log.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

//************************Deposit Test****************************************************************

@Test(dataProviderClass=Util.DataReader.class,dataProvider="dp")

public void depositTest(String name) throws Exception
	{
		try
		{	
			Select accSelect = transactionPageSetup(name);
			cust=new CustomerLogin(driver);
			List<WebElement> drp=accSelect.getOptions();
			for(int i=0;i<drp.size();i++)
			{
			Select depAcc = new Select (driver.findElement(cust.getAccNumDrp()));
			depAcc.selectByIndex(i);
			Util.Timer.wait(cust.getDeposit());
			ExtentUtil.click(cust.getDeposit(), "Deposit Button");
			Util.Timer.wait(cust.getAmount());
			ExtentUtil.type(cust.getAmount(), "1000", "Amount");
			ExtentUtil.click(cust.getDepositButton(), "Deposit Amount Button");
			Util.Timer.wait(cust.getDepositMessage());
			WebElement msg=driver.findElement(cust.getDepositMessage());
			String actualMsg=msg.getText();
			String expectedMsg=  "Deposit Successful";
			Assert.assertEquals(actualMsg,expectedMsg,"Deposit Success Message Failed");
			
			}
		}
		catch(Exception e)
		{
			ExtentList.test.fail(e.getMessage());
			Assert.fail();
		}
		
	}
		
//**********************************WithDraw TestCase****************************************************

@Test(dataProviderClass=Util.DataReader.class,dataProvider="dp")
	public void withdrawTest(String name)
	{
		try {
		Select accNums = transactionPageSetup(name);
		cust= new CustomerLogin(driver);
		List <WebElement> acNum=accNums.getOptions();
		for (int i=0;i<acNum.size();i++)
		{
			accNums.selectByIndex(i);
			Util.Timer.wait(cust.getWithdraw());
			ExtentUtil.click(cust.getWithdraw(), "Withdrawl");
			Util.Timer.wait(cust.getAmount());
			ExtentUtil.type(cust.getAmount(), "100", "Withdrawl Amount");
			ExtentUtil.click(cust.getamountWithdraw(), "Withdraw Amount Button");
			Util.Timer.wait(cust.getWithdrawMessage());
			WebElement msg=driver.findElement(cust.getWithdrawMessage());
			String actualMsg=msg.getText();
			String expectedSuccessMsg="Transaction successful";
			String expectedFailedMsg="Transaction Failed. You can not withdraw amount more than the balance.";
			WebElement bal=driver.findElement(cust.getBalAmount());
			int balAmount=Integer.parseInt(bal.getText());
			if (cust.withDraw(balAmount))
			{
				Assert.assertEquals(actualMsg,expectedSuccessMsg,"Success Message not Matched");
			}
			else
			{
				Assert.assertEquals(actualMsg,expectedFailedMsg,"Transaction Failed Message not Matched");
			}
			driver.navigate().refresh();
			Util.Timer.wait(cust.getAccNumDrp());
			accNums= new Select(driver.findElement(cust.getAccNumDrp()));
			acNum=accNums.getOptions();
		}
		}
		catch(Exception e)
		{
			ExtentList.test.fail(e.getMessage());
			Assert.fail();
		}
	}
}


