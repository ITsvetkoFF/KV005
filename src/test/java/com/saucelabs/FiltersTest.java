package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by nklimotc on 21.10.2014.
 */

public class FiltersTest {

    WebDriver driver;
    AnyPage anyPage;
    MapPage2 mapPage;
    ProblemPage problemPage;

    public String afterDate;
    public String beforeDate;
    public static int typeNumber;
    public static double latitude;
    public static double longitude;

    @BeforeSuite
    public void setUp() {
        this.driver = new FirefoxDriver();
        this.anyPage = new AnyPage(driver);
        this.mapPage = new MapPage2(driver);
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
        mapPage.clickAtProblemByCoordinate(latitude, longitude);
        Assert.assertTrue(problemPage.getProblemType().equals(mapPage.getFilterTitle(typeNumber)));
    }

    @Test
    public void checkFiltersPositive() throws Exception {
        mapPage.clickZoomOut();
        mapPage.openFiltersBoard();
        mapPage.setAfterDate(afterDate);
        mapPage.setBeforeDate(beforeDate);
        mapPage.selectOnlyOneFilter(typeNumber);
        mapPage.clickAtProblemByCoordinate(latitude, longitude);
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
