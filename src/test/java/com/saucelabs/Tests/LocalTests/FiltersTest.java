package com.saucelabs.Tests.LocalTests;

import com.saucelabs.AnyPage;
import com.saucelabs.MapPage;
import com.saucelabs.ProblemPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by nklimotc on 21.10.2014.
 */

public class FiltersTest {

    WebDriver driver;
    AnyPage anyPage;
    MapPage mapPage;
    ProblemPage problemPage;

    public String afterDate = "31 жовт. 2014";
    public String beforeDate = "01 лист. 2014";
    public static int typeNumber = 1;
    public static double latitude = 50.255;
    public static double longitude = 30.255;

    @BeforeSuite
    public void setUp() {

        this.driver = new FirefoxDriver();
        this.anyPage = new AnyPage(driver);
        this.mapPage = new MapPage(driver);
        this.problemPage = new ProblemPage(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://127.0.0.1:8090/#/map");
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void turnDown() {
        this.driver.quit();
    }

   @Test
   public void checkFiltersNegative() throws Exception {
        mapPage.clickZoomOut();
        mapPage.openFiltersBoard();
        mapPage.setAfterDate(afterDate);
        mapPage.setBeforeDate(beforeDate);
        mapPage.selectOnlyOneFilter(typeNumber);
        mapPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        Assert.assertTrue(problemPage.getProblemType().equals(mapPage.getFilterTitle(typeNumber)));
    }

    @Test
    public void checkFiltersPositive() throws Exception {
        String imageURLsString = "";
        String imageCommentsString = "";
        anyPage.logIn("admin@.com","admin");
        anyPage.addProblemToVisibleCenter(50.255, 30.255, "ProblemFor Проблеми лісів", "Проблеми лісів", "Decsription", "problemProposeTest", Arrays.asList(imageURLsString.split("\n")), Arrays.asList(imageCommentsString.split("\n")));
        anyPage.logOut();
        mapPage.clickZoomOut();
        mapPage.openFiltersBoard();
        mapPage.setAfterDate(afterDate);
        mapPage.setBeforeDate(beforeDate);
        mapPage.selectOnlyOneFilter(typeNumber);
        mapPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        Assert.assertTrue(problemPage.getProblemType().equals(mapPage.getFilterTitle(typeNumber)));
    }

    @Test
    public void datePickersButtons(){

    }
 }
//        for dataProvider

//        anyPage.addProblem(50.2, 30.2, "ProblemFor Проблеми лісів", "Проблеми лісів", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment1");

//        anyPage.addProblem(50.3, 30.3, "ProblemFor Сміттєзвалища", "Сміттєзвалища", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment2");

//        anyPage.addProblem(50.4, 30.4, "ProblemFor Незаконна забудова", "Незаконна забудова", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment3");
//
//        anyPage.addProblem(50.5, 30.5, "ProblemFor Проблеми водойм", "Проблеми водойм", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment4");
//
//        anyPage.addProblem(50.6, 30.6, "ProblemFor Загрози біорізноманіттю", "Загрози біорізноманіттю", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment5");
//
//        anyPage.addProblem(50.7, 30.7, "ProblemFor Браконьєрство", "Браконьєрство", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment6");
//
//        anyPage.addProblem(50.8, 30.8, "ProblemFor Інші проблеми", "Інші проблеми", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment7");
//
