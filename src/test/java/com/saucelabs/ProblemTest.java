package com.saucelabs;

import com.saucelabs.utils.ImageDistanceCalculator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
    public List<String> imageUrls = Arrays.asList("http://i.imgur.com/HHXCVbs.jpg", "http://i.imgur.com/1K6AdCH.jpg", "http://i.imgur.com/1K6AdCH.jpg", "http://i.imgur.com/1K6AdCH.jpg");
    public List<String> imageComments = Arrays.asList("comment1", "comment2", "\"http://i.imgur.com/1K6AdCH.jpg\"", "\"http://i.imgur.com/1K6AdCH.jpg\"");

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
//        problemPage.addProblem(latitude, longitude, problemNameTest, problemTypeTest, problemDescriptionTest, problemProposeTest, imageUrls, imageComments);
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
    public void problemCheckImage() throws IOException {
        List<String> gettedUrls = problemPage.getImageURLs();
        for(int i = 0; i < gettedUrls.size(); i++) {
            Assert.assertTrue(ImageDistanceCalculator.isImagesSimilar(gettedUrls.get(i), imageUrls.get(i)));
        }
    }

    @Test(dependsOnMethods = {"problemCheckImage"})
    public void problemCheckImageComment() {
        List<String> gettedComments = problemPage.getImagesComments();
        for(int i = 0; i < gettedComments.size(); i++){
            Assert.assertTrue(gettedComments.get(i).equals(imageComments.get(i)));
        }
    }
}
