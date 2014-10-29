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

        driver.get("http://localhost:8090/#/map");
//        driver.get("http://176.36.11.25/#/map");
        driver.manage().window().maximize();

        ProblemPage problemPage = new ProblemPage(driver);
        AnyPage anyPage = new AnyPage(driver);
        AddProblemTest addProblemTest = new AddProblemTest();

        anyPage.clickAtProblemByCoordinate(addProblemTest.latitude, addProblemTest.longitude);

        Assert.assertTrue(problemPage.getProblemTitle().equals(addProblemTest.problemNameTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(addProblemTest.problemDescriptionTest));
        Assert.assertTrue(problemPage.getProblemPropose().equals(addProblemTest.problemProposeTest));
        Assert.assertTrue(problemPage.getProblemType().equals(addProblemTest.problemTypeTest));

        System.out.println(problemPage.getProblemType() + "\n" + problemPage.getProblemTitle() + "\n" +
                problemPage.getProblemDescription() + "\n" + problemPage.getProblemPropose());
        driver.quit();
    }

}
