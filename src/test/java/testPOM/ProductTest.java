package testPOM;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ProductPage;
import pages.LoginPage;

public class ProductTest extends TestUtil {

    @Test
    public void addItemToTheCart(){
        LoginPage loginTest = new LoginPage(driver);
        ProductPage productPage = loginTest.login("standard_user", "secret_sauce");

        productPage.addItemToTheCart("bike-light");
        Assert.assertEquals(productPage.getItemsInCart(), 1);
    }

    @Test
    public void addItemsToTheCart(){
        LoginPage loginTest = new LoginPage(driver);
        ProductPage productPage = loginTest.login("standard_user", "secret_sauce");

        SoftAssert softAssert = new SoftAssert();

        productPage.addItemToTheCart("bolt-t-shirt");
        softAssert.assertEquals(productPage.getItemsInCart(), 2, "Element was not added to the cart");

        productPage.addItemToTheCart("bike-light");
        softAssert.assertEquals(productPage.getItemsInCart(), 4, "Element was not added to the cart");

        softAssert.assertAll();
    }
}
