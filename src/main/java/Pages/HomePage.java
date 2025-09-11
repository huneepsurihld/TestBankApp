package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Base.BaseClass;
import Util.ExtentUtil;

public class HomePage extends BaseClass
{
	public static String actualURL;
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	private By home =By.xpath("//button[@ng-click='home()']");
	private By sample =By.xpath("//a[text()='Sample Form']");
	private By customerLogin = By.xpath("//button[@ng-click='customer()']");
	private By bankManagerLogin =By.xpath("//button[@ng-click='manager()']");
	private By lifetimeMembership = By.xpath("//a[text()='Lifetime Membership']");
	
	public By getLifeMmbrship()
	{
		return lifetimeMembership;
	}
	
	public By getBankMngrLogin()
	{
		return bankManagerLogin;
	}
	
	public By getCustLogin()
	{
		return customerLogin;
	}
	
	
	public By getSample()
	{
		return sample;
	}
	
	public By getHome()
	{
		return home;
	}
	
	
	public void home()
	{
		Util.Timer.wait(home);
		ExtentUtil.click(home, "Home Button");
		
	}
	
	
	public SampleForm sampleForm()
	{
		
		Util.Timer.wait(sample);
		ExtentUtil.click(sample, "Sample Form");
		
		return new SampleForm(driver);
	}
	
	public CustomerLogin custLogin()
	{
		actualURL="https://www.way2automation.com/angularjs-protractor/banking/#/customer";
		ExtentUtil.click(customerLogin, "Customer Login");
		return new CustomerLogin(driver);
	}
	
	public BankManagerLogin bankMngrLogin()
	{
		
		Util.Timer.wait(bankManagerLogin);
		ExtentUtil.click(bankManagerLogin, "Bank Manager Login");
		actualURL="https://www.way2automation.com/angularjs-protractor/banking/#/manager";
		return new BankManagerLogin(driver);
	}
	
	public LifeTimeMembership lifetimeMembership()
	{
		Util.Timer.wait(lifetimeMembership);
		ExtentUtil.click(lifetimeMembership, "Lifetime Memebership");
		actualURL ="https://www.way2automation.com/lifetime-membership-club/";
		return new LifeTimeMembership(driver);
		
	}

}
