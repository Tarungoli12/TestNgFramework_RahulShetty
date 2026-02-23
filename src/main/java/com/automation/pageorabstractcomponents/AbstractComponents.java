package com.automation.pageorabstractcomponents;

import com.automation.pages.CartPage;
import com.automation.pages.OrdersPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    @FindBy(css = "button[routerlink='/dashboard/cart']")
    WebElement clickOnCartBtn;

    @FindBy(css = "button[routerlink='/dashboard/myorders']")
    WebElement clickOnMyOrdersBtn;

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public void jsSendText(String text, WebElement element) {
        js.executeScript("arguments[0].value='" + text + "'", element);
    }

    public void scroll() {
        js.executeScript("window.scrollBy(0,500)");
    }

    public CartPage goToCartPage() {
        clickOnCartBtn.click();
        return new CartPage(driver);
    }

    public OrdersPage goToOrdersPage(){
        clickOnMyOrdersBtn.click();
        return new OrdersPage(driver);
    }
}
