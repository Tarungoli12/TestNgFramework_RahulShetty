package com.automation.pages;

import com.automation.pageorabstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

public class LoginPage extends AbstractComponents {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement emailTextBox;

    @FindBy(id = "userPassword")
    WebElement passwordTextBox;

    @FindBy(id = "login")
    WebElement loginBtn;

    @FindBy(css = "div[role='alert']")
    WebElement loginErrorMessage;

    public void openWebsite(Properties properties) {
        driver.get(properties.getProperty("url"));
    }

    public HomePage loginToWebsite(String userName, String password) {
        emailTextBox.sendKeys(userName);
        passwordTextBox.sendKeys(password);
        loginBtn.click();
        return new HomePage(driver);
    }

    public String getErrorMessage(){
        waitForElementToBeVisible(loginErrorMessage);
        return loginErrorMessage.getText().trim();
    }

}
