package com.automation.testcomponents;

import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {


    static WebDriver driver;
    Properties properties;

    public LoginPage loginPage;
    public HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void initializeDriver() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/GlobalData.properties"));
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):properties.getProperty("browser");
        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        loginPage.openWebsite(properties);
    }

    public WebDriver getDriver(){
        return driver;
    }
    public String getProp(String key) {
        return properties.getProperty(key);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.close();
    }



    public String getScreenshotAndPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(src, dest);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }


    @Test
    public String takeScreenshotAsFile() {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        String screenshotFolderPath = "report/";
        String screenshotName = "screenshot.png";
        try {
            FileUtils.copyFile(screenshot, new File(screenshotFolderPath + screenshotName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshotName;
    }
}
