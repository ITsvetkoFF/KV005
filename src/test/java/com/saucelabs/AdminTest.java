package com.saucelabs;

/**
 * Created by ykadytc on 20.10.2014.
 */

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdminTest {
    //@Test
    public void adminLogin() {
            WebDriver driver = new FirefoxDriver();

            driver.get("http://localhost:8090");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            JavascriptExecutor js = null;
            if (driver instanceof JavascriptExecutor) {
                js = (JavascriptExecutor) driver;
            }
            ;
            js.executeScript("navigator.geolocation.getCurrentPosition = function(success) { success({coords: {latitude: 50.649460, longitude: 30.731506}}); }");
            AdminPage adminPage = new AdminPage(driver);
            adminPage.login("admin@.com", "admin");

            Assert.assertTrue(1 + 1 == 2);
            driver.quit();
        }
    @Test
    public void adminGetProblems() {
            int count;
            String[] problemNames;
            List<WebElement> problems;
            WebDriver driver = new FirefoxDriver();

            driver.get("http://localhost:8090");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            JavascriptExecutor js = null;
            if (driver instanceof JavascriptExecutor) {
                js = (JavascriptExecutor) driver;
            }
            ;
            js.executeScript("navigator.geolocation.getCurrentPosition = function(success) { success({coords: {latitude: 50.649460, longitude: 30.731506}}); }");
            AdminPage adminPage = new AdminPage(driver);
            adminPage.login("admin@.com", "admin");
            //count = adminPage.getProblemForModerationCount();
            //problemNames = adminPage.getProblemForModerationNames();
            problems = adminPage.getProblems();
            for (WebElement problem: problems) {
                problem.click();
                Assert.assertTrue(problem.getText().equals(adminPage.getH1()));
            }
            //System.out.println(count);
            //System.out.println(problems);
            //Assert.assertTrue(1 + 1 == 2);
            driver.quit();
        }
    }