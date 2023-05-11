package com.saucedemo;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductTest extends TestUtil {
    private final static String PRODUCT_ID = "add-to-cart-sauce-labs-";

    @Test (dataProvider = "items list")
    public void addItemIntoCart(String itemName){
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.xpath("//input[@name='login-button']"));
        loginBtn.click();

        WebElement itemToBeAdded = driver.findElement(By.id(PRODUCT_ID + itemName));
        itemToBeAdded.click();

        WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_badge"));

        Assert.assertEquals(shoppingCartBadge.getText(), "1");
    }

    //trigger the test
    @DataProvider(name = "items list")
    public Object[][] getItems(){
        return new Object[][]{
            {"bike-light"},
                {"bolt-t-shirt"}
        };
    }
}
