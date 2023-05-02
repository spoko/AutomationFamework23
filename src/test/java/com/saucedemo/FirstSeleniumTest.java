package com.saucedemo;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FirstSeleniumTest extends TestUtil {

    @Test(dataProvider = "correctUsers")
    public void successfulLoginSauceDemo(String username, String password) throws InterruptedException {
        //driver.manage().window().fullscreen();
        //*[@id="root"]/div/div[2]/div[2]/div/div[2]/h4
        //*[@id="root"]/div/div[2]/div[2]/div/div[2]/h4

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//input[@name='login-button']"));
        loginBtn.click();

        WebElement productsPageTitle = driver.findElement(By.cssSelector(".title"));
        WebElement prPageTitle = driver.findElement(By.className("title"));
        WebElement prsPageTitle = driver.findElement(By.xpath("//span[@class='title']"));

        Assert.assertTrue(productsPageTitle.isDisplayed());

        WebElement menuBtn = driver.findElement(By.id("react-burger-menu-btn"));
        Assert.assertTrue(menuBtn.isEnabled());
        menuBtn.click();

        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));

        //shall never use thread.sleep fo waiting!!!
        Thread.sleep(1000);
        Assert.assertTrue(logoutLink.isDisplayed());

    }

    @DataProvider (name = "wrongUsers")
    public Object [][] getUsers(){
        return new Object[][]{
                {"wrongUsername", "secret_sauce"},
                {"standard_user", "wrongPassword"},
                {"wrong", "wrong"}
        };
    }

    @Test (dataProvider = "wrongUsers")
    public void unsuccessfulLogin(String username, String password){
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//input[@name='login-button']"));
        loginBtn.click();

        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @DataProvider(name = "correctUsers")
    public Object[][] readUsersFromCsv() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctUsers.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object [][] csvDataObj = new Object[csvData.size()][2];

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObj[i] = csvData.get(i);
            }
            return csvDataObj;
        } catch (IOException e) {
            System.out.println("Not Possible to find the file!");
            return null;
        } catch (CsvException e){
            return null;
        }
    }
}
