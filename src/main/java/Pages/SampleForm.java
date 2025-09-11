package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.BaseClass;

public class SampleForm extends BaseClass{

	public SampleForm(WebDriver driver)
	{
		super(driver);
	}
		
	private By firstName = By.id("firstName");
	private By lastName = By.id("lastName");
	private By email =By.id("email");
	private By pwd = By.id("password");
	private By reading = By.xpath("//input[@value='Reading']");
	private By traveling = By.xpath("//input[@value='Traveling']");
	private By sports=By.xpath("//input[@value='Sports']");
	private By gender = By.id("gender");
	private By about= By.id("about");
	private By register =By.xpath("//button[text()='Register']");
	private By message = By.id("successMessage");
	
	
	public By getRegister()
	{
		return register;
	}
	
	public By getMessage()
	{
		return message;
	}
	
	public By getEmail()
	{
		return email;
	}
	
	public By getPwd()
	{
		return pwd;
	}
	
	public By getReading()
	{
		return reading;
	}
	
	public By getTraveling()
	{
		return traveling;
	}
	
	public By getSports()
	{
		return sports;
	}
	
	public By getGender()
	{
		return gender;
	}
	
	public By getAbout()
	{
		return about;
	}
	
	public By getLastName()
	{
		return lastName;
	}
	
	public By getFirstName()
	{
		return firstName;
	}
	
	
	public void enterValues()
		{
			
			
		}
	}


