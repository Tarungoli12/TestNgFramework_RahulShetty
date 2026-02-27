package com.automation.tests;

import com.automation.pages.CartPage;
import com.automation.pages.HomePage;
import com.automation.pages.OrdersPage;
import com.automation.pages.PaymentPage;
import com.automation.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrderHistoryTest extends BaseTest {
    String productName = "IPHONE";
    String country = "India";

    @Test(dataProvider = "getData",groups = {"dataProviderTest"})
    public void placeOrder(String user,String pass, String prod) {
        HomePage homePage = loginPage.loginToWebsite(user, pass);
        Assert.assertTrue(homePage.VerifyHomePage());

        homePage.AddProductToCartBasedOnProductName(prod);
        homePage.waitForProductAddedToCartMessageIsDisplayed();

        CartPage cartPage = homePage.goToCartPage();
        Assert.assertTrue(cartPage.getProductsInCartAndVerifyTheProductSameAsOurExpectation(prod));
        PaymentPage paymentPage = cartPage.clickOnCheckout();

        paymentPage.selectCountry(country);
        paymentPage.clickOnPlaceOrder();

        Assert.assertTrue(paymentPage.getOrderConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"placeOrder"})
    public void orderHistoryTest(){
        loginPage.loginToWebsite(getProp("username"), getProp("password"));
        Assert.assertTrue(homePage.VerifyHomePage());
        OrdersPage ordersPage = loginPage.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrder(productName));
    }


    @DataProvider
    public Object[][] getData() {
        return new Object[][]{{"demo845@gmail.com","Demo@845","ZARA"},{"demo845@gmail.com","Demo@845","IPHONE"}};
    }

}
