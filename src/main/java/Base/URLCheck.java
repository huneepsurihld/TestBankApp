package Base;

import org.testng.Assert;

import Util.ExtentList;

public class URLCheck extends BaseTest {
	
	public static void urlCheck(String value, String expected)
	{
		try
		{
			Util.UrlTimer.urlTimerWait(value);
			String actual = driver.getCurrentUrl();
			Assert.assertEquals(actual, expected,"URL not matched");
			log.info("URL matched");
		}
		catch (Throwable e)
		{
			log.error(e.getMessage());
			ExtentList.test.fail(e.getMessage());
			throw e;
		}
		
	}

}
