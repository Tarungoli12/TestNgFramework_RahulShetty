package com.automation.pages;

import com.automation.pageorabstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[@class='itemNumber'] /following-sibling::h3")
    List<WebElement> itemsName;

    @FindBy(css = "div[class*='subtotal'] button")
    WebElement checkOutBtn;

    public boolean getProductsInCartAndVerifyTheProductSameAsOurExpectation(String productName) {
        waitForElementToBeVisible(checkOutBtn);
        return itemsName.stream().anyMatch(s -> s.getText().contains(productName));
    }

    public PaymentPage clickOnCheckout() {
        jsClick(checkOutBtn);
        return new PaymentPage(driver);
    }
}
