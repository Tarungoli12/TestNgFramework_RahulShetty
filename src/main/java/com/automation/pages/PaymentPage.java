package com.automation.pages;

import com.automation.pageorabstractcomponents.AbstractComponents;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends AbstractComponents {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[class*='action__submit']")
    WebElement placeOrderBtn;

    @FindBy(tagName = "h1")
    WebElement orderConfirmationText;

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement countryInputBox;

    @FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
    WebElement selectCountry;

    public void waitForCountryTypeBoxIsDisplayed() {
        waitForElementToBeVisible(countryInputBox);
    }

    public void selectCountry(String country) {
        countryInputBox.sendKeys(country);
        jsClick(selectCountry);
    }

    public void clickOnPlaceOrder() {
        jsClick(placeOrderBtn);
    }

    public String getOrderConfirmationMessage() {
        System.out.println(orderConfirmationText.getText().trim());
        return orderConfirmationText.getText().trim();
    }

}
