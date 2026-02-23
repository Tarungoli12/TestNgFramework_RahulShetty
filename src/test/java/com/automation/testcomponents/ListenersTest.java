package com.automation.testcomponents;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import static com.automation.tests.ExtentReportDemoTest.extentReport;

public class ListenersTest extends BaseTest implements ITestListener {

    ExtentReports extent = extentReport();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        test=extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        String filepath=null;
        try {
            filepath=getScreenshotAndPath(result.getMethod().getMethodName(),getDriver());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(filepath);
        extentTest.get().addScreenCaptureFromPath(filepath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.PASS,"Test Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
