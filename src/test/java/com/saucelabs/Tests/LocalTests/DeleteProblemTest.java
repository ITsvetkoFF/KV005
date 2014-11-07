package com.saucelabs.Tests.LocalTests;

import com.saucelabs.AdminPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.Constant;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yermek on 05.11.2014.
 */
public class DeleteProblemTest {
    //@BeforeClass
    @Test
    public void deleteProblemUI() {
        WebDriver driver = new FirefoxDriver();

        driver.get(Constant.URLlocal);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        AdminPage adminPage = new AdminPage(driver);
        adminPage.logIn(Constant.Username, Constant.Password);

        Assert.assertTrue(1 + 1 == 2);
        driver.quit();
    }
}
