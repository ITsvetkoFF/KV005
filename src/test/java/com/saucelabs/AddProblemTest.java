package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Roma on 21.10.2014.
 */
public class AddProblemTest {
    public double latitude = 50.1;
    public double longitude = 30.1;
    public String problemNameTest = "problemNameTest";
    public String problemTypeTest = "Загрози біорізноманіттю";
    public String problemDescriptionTest = "problemDescriptionTest";
    public String problemProposeTest = "problemProposeTest";
    public List<String> imageUrls = Arrays.asList("http://i.imgur.com/HHXCVbs.jpg", "http://i.imgur.com/gYx3HoD.png");
    public List<String> imageComments = Arrays.asList("comment1", "comment2");


    @Test
    public void addProblemTest() {

        WebDriver driver = new FirefoxDriver();
        AnyPage anyPage = new AnyPage(driver);

//        driver.get("http://localhost:8090/#/map");
        driver.get("http://176.36.11.25/#/map");
        driver.manage().window().maximize();

        anyPage.logIn("admin@.com", "admin");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        anyPage.addProblem(latitude, longitude, problemNameTest, problemTypeTest, problemDescriptionTest, problemProposeTest, imageUrls, imageComments);

        driver.navigate().refresh();
        anyPage.clickAtProblemByCoordinate(latitude, longitude);

        Assert.assertTrue(true);
        driver.quit();
    }
}
