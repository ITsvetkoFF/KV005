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

        ProblemPage problemPage = new ProblemPage(driver);
        MapPage mapPage = new MapPage(driver);
        AddProblemTest addProblemTest = new AddProblemTest();

        driver.get("http://localhost:8090/#/map");
//        driver.get("http://176.36.11.25/#/map");
        driver.manage().window().maximize();

        mapPage.clickAtProblemByCoordinate(addProblemTest.latitude, addProblemTest.longitude);

        Assert.assertTrue(problemPage.getProblemTitle().equals(addProblemTest.problemNameTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(addProblemTest.problemDescriptionTest));
        Assert.assertTrue(problemPage.getProblemPropose().equals(addProblemTest.problemProposeTest));

        driver.quit();
    }
}
