package com.automation.cucumber.stepDefinitions;

import com.automation.pages.CartPage;
import com.automation.pages.HomePage;
import com.automation.pages.PaymentPage;
import com.automation.testcomponents.BaseTest;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinition extends BaseTest {
    HomePage homePage;
    CartPage cartPage;
    PaymentPage paymentPage;

    @Given("I opened Shopping Website")
    public void i_opened_shopping_website() {
        try {
            initializeDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Given("logged with username {string} and password {string}")
    public void logged_with_username_and_password(String username, String password) {
        homePage = loginPage.loginToWebsite(username, password);
    }
    @When("I add product {string} to cart")
    public void i_add_product_to_cart(String prod) {
        homePage.AddProductToCartBasedOnProductName(prod);
        homePage.waitForProductAddedToCartMessageIsDisplayed();
    }
    @When("Checkout {string} and submit the order")
    public void checkout_and_submit_the_order(String prod) {
        cartPage = homePage.goToCartPage();
        Assert.assertTrue(cartPage.getProductsInCartAndVerifyTheProductSameAsOurExpectation(prod));
        paymentPage = cartPage.clickOnCheckout();

        paymentPage.waitForCountryTypeBoxIsDisplayed();
        paymentPage.selectCountry("India");
        paymentPage.clickOnPlaceOrder();
    }
    @Then("{string} message is displayed on confirmationPage")
    public void message_is_displayed_on_confirmation_page(String message) {
        Assert.assertTrue(paymentPage.getOrderConfirmationMessage().
                equalsIgnoreCase(message));
    }

    @Then("{string} message is displayed")
    public void message_is_displayed(String message){
        Assert.assertEquals(loginPage.getErrorMessage(), message);
    }

}
