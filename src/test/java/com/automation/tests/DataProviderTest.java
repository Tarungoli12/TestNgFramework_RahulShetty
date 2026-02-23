package com.automation.tests;

import com.automation.pages.CartPage;
import com.automation.pages.HomePage;
import com.automation.pages.PaymentPage;
import com.automation.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class DataProviderTest extends BaseTest {
    String country = "India";

    @Test(dataProvider = "getData",groups = {"dataProviderTest"})
    public void placeOrder(HashMap<String,String> data) {

        HomePage homePage = loginPage.loginToWebsite(data.get("email"), data.get("password"));
        Assert.assertTrue(homePage.VerifyHomePage());

        homePage.AddProductToCartBasedOnProductName(data.get("product"));
        homePage.waitForProductAddedToCartMessageIsDisplayed();

        CartPage cartPage = homePage.goToCartPage();
        Assert.assertTrue(cartPage.getProductsInCartAndVerifyTheProductSameAsOurExpectation(data.get("product")));
        PaymentPage paymentPage = cartPage.clickOnCheckout();

        paymentPage.selectCountry(country);
        paymentPage.clickOnPlaceOrder();

        Assert.assertTrue(paymentPage.getOrderConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @DataProvider
    public Object[][] getData(){

        HashMap<String,String> map1 = new HashMap<>();
        map1.put("email","demo845@gmail.com");
        map1.put("password","Demo@845");
        map1.put("product","ZARA");

        HashMap<String,String> map2 = new HashMap<>();
        map2.put("email","demo845@gmail.com");
        map2.put("password","Demo@845");
        map2.put("product","IPHONE");

        return new Object[][]{{map1},{map2}};
    }
}
