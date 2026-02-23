package com.automation.tests;

import com.automation.data.JSONDataReader;
import com.automation.pages.CartPage;
import com.automation.pages.HomePage;
import com.automation.pages.PaymentPage;
import com.automation.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class JSONDataProviderTest extends BaseTest {

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
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> dataReader = JSONDataReader.getJsonDataToMap("src/test/java/com/automation/data/JSONData.json");
//      return new Object[][]{{dataReader.get(0)}, {dataReader.get(1)}};
        System.out.println(dataReader.size());
        Object[][] data = new Object[dataReader.size()][1];
        for (int i = 0; i < dataReader.size(); i++) {
            System.out.println(dataReader.get(i));
            data[i][0] = dataReader.get(i);
        }
        return data;
    }

}
