package com.automation.pages;

import com.automation.pageorabstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends AbstractComponents {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[routerlink='/dashboard/']")
    WebElement homeBtn;

    @FindBy(css = ".mb-3")
    List<WebElement> productsInHomePage;

    By addToCartBtn = By.cssSelector(".card-body button:last-of-type");

    @FindBy(css = "div[aria-label='Product Added To Cart']")
    WebElement productAddedToCartMessage;



    public boolean VerifyHomePage() {
        waitForElementToBeVisible(homeBtn);
        return homeBtn.isDisplayed();
    }

    public WebElement getProductName(String productName) {
        return productsInHomePage.stream().filter(s -> s.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);
    }

    public void AddProductToCartBasedOnProductName(String prod) {
        WebElement element = getProductName(prod);
        element.findElement(addToCartBtn).click();
    }

    public void waitForProductAddedToCartMessageIsDisplayed() {
        waitForElementToBeVisible(productAddedToCartMessage);
    }




}
