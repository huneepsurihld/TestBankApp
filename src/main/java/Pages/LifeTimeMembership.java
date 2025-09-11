package Pages;

import org.openqa.selenium.WebDriver;

import Base.BaseClass;

public class LifeTimeMembership extends BaseClass{

	public LifeTimeMembership(WebDriver driver) 
	{
		super(driver);
	}

	public String URL ()
	{
		String url ="https://www.way2automation.com/lifetime-membership-club/";
		return url;
	}
	
}
