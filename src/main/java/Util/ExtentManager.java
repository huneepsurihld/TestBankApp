package Util;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager 
{
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	static Date d = new Date();
	static String name = d.toString().replace(":", "").replace(" ", "");
	
	public static synchronized ExtentReports extentMngr()
	{
		htmlReporter = new ExtentSparkReporter("D:\\Document\\Default Location\\PageObjectBank\\test-output\\Reports\\"+name+".html");
		htmlReporter.config().setDocumentTitle("Automation Bank site");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setReportName("Bank Sample Test");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Author", "Honey");
		extent.setSystemInfo("Env", "windows");
		
		return extent;
	}
	
	
}
