package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Roma on 21.10.2014.
 */
public class AddProblemTest {

    @Test
    public void addProblemTest() {

        WebDriver driver = new FirefoxDriver();

        AnyPage anyPage = new AnyPage(driver);

        driver.get("http://176.36.11.25/#/map");

        driver.manage().window().maximize();

        anyPage.logIn("admin@.com", "admin");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        anyPage.addProblem(50.1, 30.1, "problemNameTest", "problemTypeTest", "problemDescriptionTest", "problemProposeTest");

        Assert.assertTrue(true);
        driver.quit();
    }
}
