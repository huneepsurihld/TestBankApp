package Util;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Base.BaseTest;

public class ExtentList implements ITestListener

{
	
	public static ExtentReports extent=ExtentManager.extentMngr();
	public static Markup m;
	private static ThreadLocal <ExtentTest> threadLocal=new ThreadLocal<>();
	public static ExtentTest test;
	
	
    @Override
    public void onTestStart(ITestResult result) 
    {
    	String testName = result.getMethod().getTestClass().getName()+" : "+result.getMethod().getMethodName();
    	String paramName=result.getTestContext().getCurrentXmlTest().getParameter("name");
    	String authorName=result.getTestContext().getCurrentXmlTest().getParameter("author");
    	ExtentTest extentTest= extent.createTest(testName+" "+ " {Browser}: "+paramName);
       	extentTest.assignAuthor(authorName);
    	String message = "Class Name: "+result.getMethod().getTestClass().getName()+". Method name: "+result.getMethod().getMethodName()+". Browser Name: "+paramName;
        m=MarkupHelper.createLabel(message, ExtentColor.BLUE);
        threadLocal.set(extentTest);
        test=threadLocal.get();
        test.info(m);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.log.info("Test Passed: " + result.getName());
        m=MarkupHelper.createLabel("PASSED", ExtentColor.GREEN);
        test.pass(m); 
       
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest.log.info("Test FAILED"+result.getMethod().getMethodName());
        m=MarkupHelper.createLabel("Failed", ExtentColor.RED);
        test.fail(m);
			try {
				Util.Screenshot.snapshot(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
	 }

    @Override
    public void onTestSkipped(ITestResult result) {
        BaseTest.log.info("Test Skipped: " + result.getName());
        
       String reason = " ";
        if(result.getThrowable() !=null)
       {
    	   reason=result.getThrowable().getMessage();
       }
        else 
        {
        	reason="Exception with some other reasons, no Exception captured";
        }
        m=MarkupHelper.createLabel("Test case Skipped due to "+reason, ExtentColor.BROWN);
        test.skip(m);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started");
    }

    @Override
    public void onFinish(ITestContext context) {
        if(extent!=null)
        	{extent.flush();
    }
        }

}
