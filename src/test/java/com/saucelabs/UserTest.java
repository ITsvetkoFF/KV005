package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yermek on 23.10.2014.
 */
public class UserTest {
    @Test
    public void userRegisterTest() {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://localhost:8090");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        AnyPage anyPage = new AnyPage(driver);
        anyPage.register("TestName", "TestSurname", "test8@test.com", "test");
        Assert.assertTrue("TestName TestSurname".toUpperCase().equals(anyPage.getLoggedInUserName().toUpperCase()));
        anyPage.logOut();
        driver.quit();
    }

}
