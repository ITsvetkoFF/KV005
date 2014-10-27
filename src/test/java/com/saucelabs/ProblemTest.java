package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by onikistc on 21.10.2014.
 */
public class ProblemTest {
    @Test
    public void checkProblemInfo() {
        WebDriver driver = new FirefoxDriver();

//        driver.get("http://localhost:8090/#/map");
        driver.get("http://176.36.11.25/#/map");

        ProblemPage problemPage = new ProblemPage(driver);
        MapPage mapPage = new MapPage(driver);

        mapPage.clickAtProblemByCoordinate(AddProblemTest.latitude, AddProblemTest.longitude);

        Assert.assertTrue(problemPage.getProblemTitle().equals(AddProblemTest.problemNameTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(AddProblemTest.problemDescriptionTest));
        Assert.assertTrue(problemPage.getProblemPropose().equals(AddProblemTest.problemProposeTest));

        Assert.assertTrue(true);
        driver.quit();
    }

}
