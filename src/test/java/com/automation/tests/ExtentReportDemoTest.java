package com.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ExtentReportDemoTest {

    WebDriver driver;
    static ExtentReports reports;

    @Test(dependsOnMethods = {"extentReport"})
    public void test(){
        ExtentTest test = reports.createTest(" First Test");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        System.out.println(driver.getTitle());
        String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        test.addScreenCaptureFromBase64String(base64);
        reports.flush();
    }
;

    @Test
    public static ExtentReports extentReport(){
        String path=System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        ExtentReports extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Tarun G");
        return extent;
    }
}
