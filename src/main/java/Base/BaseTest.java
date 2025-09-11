package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import Util.DriverManager;

public class BaseTest
{
	public static WebDriver driver;
	//public static ThreadLocal<WebDriver> driver= new ThreadLocal<>();
	private FileInputStream file;
	public static Logger log= Logger.getLogger(BaseTest.class);
	private Properties pr = new Properties();
	
	public void setup(String browser) throws Exception
	
	{
		
		try
		{
			PropertyConfigurator.configure("D:\\Document\\Default Location\\PageObjectBank\\src\\main\\resources\\Log4j.properties"); 
			file = new FileInputStream("D:\\Document\\Default Location\\PageObjectBank\\src\\main\\resources\\Config.properties");
			pr.load(file);
			DriverManager.setDriver(browser);
			driver = DriverManager.getDriver();
			driver.get(pr.getProperty("url"));
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			log.info(e);
		}
						
	}
	
	
	public static Boolean isAvailable(By locator, WebDriver driver, int time)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
				
			WebElement button=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return button.isDisplayed();
		}
		catch(Exception e)
		{
			log.info(e.getStackTrace()); 
			return false;
		}
		
	}
	
	
	public void killBrowser()
	{
		DriverManager.quitDriver();
	}
}


