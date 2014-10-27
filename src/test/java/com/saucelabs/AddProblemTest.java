package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Roma on 21.10.2014.
 */
public class AddProblemTest {
    public static double latitude = 50.1;
    public static double longitude = 30.1;
    public static String problemNameTest = "problemNameTest";
    public static String problemTypeTest = "problemTypeTest";
    public static String problemDescriptionTest = "problemDescriptionTest";
    public static String problemProposeTest = "problemProposeTest";


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

        anyPage.addProblem(latitude, longitude, problemNameTest, problemTypeTest, problemDescriptionTest, problemProposeTest);

        Assert.assertTrue(true);
        driver.quit();
    }
}
