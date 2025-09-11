package Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManager 
{
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void setDriver(String browser) throws Exception
	
	{
		WebDriver webDriver;
		switch(browser.toLowerCase())
		{
		case "chrome":
			webDriver = new ChromeDriver();
			break;
		case "firefox":
			webDriver = new FirefoxDriver();
			break;
		case "internetexplorer":
			webDriver = new InternetExplorerDriver();
			break;
		default:
			throw new Exception("Incorrect Browser");
			
		}
		driver.set(webDriver);
		
	}
	 public static WebDriver getDriver()
	 {
		 return driver.get();
	 }

public static void quitDriver()
{ WebDriver webDriver = driver.get();
	if (webDriver!=null)
	{
		webDriver.quit();
		driver.remove();
	}
}
	 
}
