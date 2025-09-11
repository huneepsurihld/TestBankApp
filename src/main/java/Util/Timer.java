package Util;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseTest;

public class Timer extends BaseTest
{
	
	public static void wait(By locator)
	{
		WebDriverWait time = new WebDriverWait(driver, Duration.ofSeconds(10));
		time.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static void waitAlert()
	{
		new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
	}

}
