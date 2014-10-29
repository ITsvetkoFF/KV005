package com.saucelabs;

import com.saucelabs.utils.ImageDistanceCalculator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by onikistc on 21.10.2014.
 */
public class ProblemTest {
    public static double latitude = 50.2;
    public static double longitude = 30.2;
    public String problemNameTest = "problemNameTest";
    public String problemTypeTest = "Загрози біорізноманіттю";
    public String problemDescriptionTest = "problemDescriptionTest";
    public String problemProposeTest = "problemProposeTest";
    public String filePath = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg";
    public String filePath2 = "http://i.imgur.com/HHXCVbs.jpg";
    public String imageComment = "bla-bla-bla";

     WebDriver driver;
     ProblemPage problemPage;

    @BeforeSuite
    public  void setUp() {
        this.driver = new FirefoxDriver();
        this.problemPage = new ProblemPage(driver);
        System.out.println("Browser open");
//        driver.get("http://176.36.11.25/#/map");
        driver.get("http://localhost:8090/#/map");
        driver.manage().window().maximize();

//        problemPage.logIn("admin@.com", "admin");
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        problemPage.addProblem(latitude, longitude, problemNameTest, problemTypeTest, problemDescriptionTest, problemProposeTest, filePath2, imageComment);
//        driver.navigate().refresh();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        problemPage.clickAtProblemByCoordinate(latitude, longitude);
    }
    @AfterSuite
    public  void turnDown() {
        this.driver.quit();
        System.out.println("Browser close\n");
    }

    @Test
    public void problemCheckTitle() {
        Assert.assertTrue(problemPage.getProblemTitle().equals(problemNameTest));
    }
    @Test(dependsOnMethods = {"problemCheckTitle"})
    public void problemCheckType() {
        Assert.assertTrue(problemPage.getProblemType().equals(problemTypeTest));
    }
    @Test(dependsOnMethods = {"problemCheckType"})
    public void problemCheckDescription() {
        Assert.assertTrue(problemPage.getProblemDescription().equals(problemDescriptionTest));
    }
    @Test(dependsOnMethods = {"problemCheckDescription"})
    public void problemCheckPropose() {
        Assert.assertTrue(problemPage.getProblemPropose().equals(problemProposeTest));
    }
    @Test(dependsOnMethods = {"problemCheckPropose"})
    public void problemCheckImage() {
        Assert.assertTrue(problemPage.getImageComment().equals(imageComment));
    }
    @Test(dependsOnMethods = {"problemCheckImage"})
    public void problemCheckImageComment() throws IOException {
        String url = problemPage.getImageURL();
        Assert.assertTrue(ImageDistanceCalculator.isImagesSimilar(url, filePath2));
    }
}
