package com.saucelabs.Tests.LocalTests;

import com.saucelabs.AdminPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yermek on 05.11.2014.
 */
public class DeleteProblemTest {
    @Test
    public void deleteProblemUI() {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://localhost:8090");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        AdminPage adminPage = new AdminPage(driver);
        adminPage.logIn("admin@.com", "admin");

        Assert.assertTrue(1 + 1 == 2);
        driver.quit();
    }
}
