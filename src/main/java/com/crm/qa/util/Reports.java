package com.crm.qa.util;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;



public class Reports implements ITestListener
{
	private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    
    
    public synchronized void  onStart(ITestContext result)
    {
        System.out.println("On Start");
        ExtentTest parent=extent.createTest(result.getCurrentXmlTest().getName());
        parentTest.set(parent);

    }
    public synchronized void onTestStart(ITestResult result) {
        System.out.println("On TestStart   ::"+result.getName());
        ExtentTest child = parentTest.get().createNode(result.getMethod().getMethodName());
        test.set(child);

    }

    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println("On TestPass");
        test.get().pass("Test passed");
        String screenShotPath = null;
        try
        {
        screenShotPath = ScreenShot.capture(result.getName());
		test.get().pass(MarkupHelper.createLabel(result.getName() + "Test case Passed" , ExtentColor.BLUE));
		test.get().log(Status.PASS,"screen shot is below"+test.get().addScreenCaptureFromPath(screenShotPath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void onTestFailure(ITestResult result) {
        System.out.println("On TestFailed");
        test.get().fail("Test failed");
        try
        {
        String screenShotPath = ScreenShot.capture(result.getName());
		test.get().fail(MarkupHelper.createLabel(result.getName() + "Test case FAILED" , ExtentColor.RED));
		test.get().log(Status.FAIL,"screen shot is below"+test.get().addScreenCaptureFromPath(screenShotPath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }



    public synchronized void onFinish(ITestContext iTestContext) {
       
        extent.flush();
    }
    public synchronized static  void logmessage(String message)
    {
        if(test.get()==null) {
            System.out.println("return it is null");
            return;
        }
        test.get().log(Status.INFO," "+message);
    }
}
