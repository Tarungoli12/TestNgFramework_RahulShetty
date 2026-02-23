package com.automation.tests;

import com.automation.pages.CartPage;
import com.automation.testcomponents.BaseTest;
import com.automation.testcomponents.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest {
    String productName = "IPHONE";

    @Test(groups = {"ErrorValidation"}, retryAnalyzer = RetryAnalyzer.class)
    public void loginErrorValidation(){
        loginPage.loginToWebsite(getProp("username"), getProp("inCorrectPassword"));
        Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email and password.");
    }

    @Test
    public void productErrorValidation(){
        loginPage.loginToWebsite(getProp("username"), getProp("password"));
        homePage.AddProductToCartBasedOnProductName(productName);
        homePage.waitForProductAddedToCartMessageIsDisplayed();

        CartPage cartPage = homePage.goToCartPage();
        Assert.assertTrue(cartPage.getProductsInCartAndVerifyTheProductSameAsOurExpectation(productName));
    }
}
