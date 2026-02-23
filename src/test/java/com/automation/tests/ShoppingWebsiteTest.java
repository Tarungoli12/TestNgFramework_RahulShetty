package com.automation.tests;

import com.automation.pages.CartPage;
import com.automation.pages.HomePage;
import com.automation.pages.PaymentPage;
import com.automation.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingWebsiteTest extends BaseTest {

    @Test
    public void cartValidation() {
        String productName = "IPHONE";
        String country = "India";

        HomePage homePage = loginPage.loginToWebsite(getProp("username"), getProp("password"));
        Assert.assertTrue(homePage.VerifyHomePage());

        homePage.AddProductToCartBasedOnProductName(productName);
        homePage.waitForProductAddedToCartMessageIsDisplayed();

        CartPage cartPage = homePage.goToCartPage();
        Assert.assertTrue(cartPage.getProductsInCartAndVerifyTheProductSameAsOurExpectation(productName));
        PaymentPage paymentPage = cartPage.clickOnCheckout();

        paymentPage.waitForCountryTypeBoxIsDisplayed();
        paymentPage.selectCountry(country);
        paymentPage.clickOnPlaceOrder();

        Assert.assertTrue(paymentPage.getOrderConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
}





        //verify the home page get the products
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
//        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        //click the add to cart based on where the prod name is same as our prod name
//        WebElement prod = productsInHomePage.stream().filter(s->s.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);
//        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //wait for msg to displayed
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Product Added To Cart']")));

        //click on cart
//        driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

        //get the products in cart by using friendly locator
//        WebElement itemName = driver.findElement(By.cssSelector("p[class='itemNumber']"));
//        List<WebElement> productsInCart = driver.findElements(with(By.tagName("h3")).straightBelow(itemName));

        //assert and print the product in cart which is same as our expected product
//        Assert.assertTrue(productsInCart.stream().anyMatch(s->s.getText().contains(productName)));
//        productsInCart.stream().map(WebElement::getText).forEach(System.out::println);

        //click on checkout
//        WebElement checkout = driver.findElement(By.cssSelector("div[class*='subtotal'] button"));
//        js.executeScript("arguments[0].click();",checkout);

        //Type country
//        WebElement country = driver.findElement(By.cssSelector("div[class='user__address'] input"));
//        wait.until(ExpectedConditions.visibilityOf(country));
//        js.executeScript("window.scrollBy(0,500)");

        //This is not working
//        js.executeScript("arguments[0].value='I'",country);
//        Thread.sleep(2000);

//        a.moveToElement(country).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();

//        js.executeScript("arguments[0].value='In'",country);
//        Thread.sleep(2000);

//        js.executeScript("arguments[0].value='Ind'",country);
//        country.sendKeys(Keys.ENTER);
//        Thread.sleep(5000);


        //click on place order
//        driver.findElement(By.cssSelector("a[class*='action__submit']")).click();

        //get the confirmation text
//        String orderConfirmation = driver.findElement(By.tagName("h1")).getText().trim();
//        Assert.assertTrue(orderConfirmation.equalsIgnoreCase(""));
