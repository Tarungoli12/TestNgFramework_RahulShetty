package com.automation.pages;

import com.automation.pageorabstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponents {
    WebDriver driver;
    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "tr[class='ng-star-inserted'] td:nth-child(3)")
    List<WebElement> productNames;

    public boolean verifyOrder(String productName){
       return productNames.stream().map(WebElement::getText).map(String::toUpperCase).
               anyMatch(s->s.contains(productName));
    }
}
