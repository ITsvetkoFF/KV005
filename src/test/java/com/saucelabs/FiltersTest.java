package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by nklimotc on 21.10.2014.
 */

public class FiltersTest{

    @Test
    public void checkFilters () throws Exception {

        WebDriver driver = new FirefoxDriver();
        AnyPage anyPage = new AnyPage(driver);
        MapPage mapPage = new MapPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);


        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("http://127.0.0.1:8090/#/map");

        driver.manage().window().maximize();

        //new AlertCloserThread().start();
        //anyPage.addProblem(50.2, 30.2, "ProblemFor Проблеми лісів", "Проблеми лісів", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment1");

        mapPage.clickZoomOut();
        mapPage.openFiltersBoard();

//        mapPage.selectAllExceptOneFilter(1);
//
//        try {
//        mapPage.clickAtProblemByCoordinate(50.2, 30.2);
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//
//        Assert.assertTrue(problemPage.getProblemType().equals(mapPage.getFilterTitle(1)));

//        anyPage.addProblem(50.3, 30.3, "ProblemFor Сміттєзвалища", "Сміттєзвалища", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment2");

//        mapPage.selectAllExceptOneFilter(2);
//        mapPage.clickAtProblemByCoordinate(50.3, 30.3);
//
//        Assert.assertTrue(problemPage.getProblemType().equals(mapPage.getFilterTitle(2)));

        mapPage.selectOnlyOneFilter(2);
        mapPage.clickAtProblemByCoordinate(50.3, 30.3);

        Assert.assertTrue(problemPage.getProblemType().equals(mapPage.getFilterTitle(2)));
//
//        mapPage.selectOnlyOneFilter(2);
//        mapPage.clickAtProblemByCoordinate(50.3, 30.3);
//
//        Assert.assertTrue(problemPage.getProblemType().equals(mapPage.getFilterTitle(2)));


//        new AlertCloserThread().start();
//        anyPage.addProblem(50.3, 30.3, "ProblemFor Сміттєзвалища", "Сміттєзвалища", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment2");
//
//        new AlertCloserThread().start();
//        anyPage.addProblem(50.4, 30.4, "ProblemFor Незаконна забудова", "Незаконна забудова", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment3");
//
//        new AlertCloserThread().start();
//        anyPage.addProblem(50.5, 30.5, "ProblemFor Проблеми водойм", "Проблеми водойм", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment4");
//
//        new AlertCloserThread().start();
//        anyPage.addProblem(50.6, 30.6, "ProblemFor Загрози біорізноманіттю", "Загрози біорізноманіттю", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment5");
//
//        new AlertCloserThread().start();
//        anyPage.addProblem(50.7, 30.7, "ProblemFor Браконьєрство", "Браконьєрство", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment6");
//
//        new AlertCloserThread().start();
//        anyPage.addProblem(50.8, 30.8, "ProblemFor Інші проблеми", "Інші проблеми", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment7");
//
//        filters.selectAllExceptOneFilter();
//        filters.selectOnlyOneFilter();
//
//        Assert.assertTrue(problemPage.getProblemType().equals(filters.getFilterTitle()));

        //Assert.assertTrue(driver.findElement(By.cssSelector(".form-control.ng-isolate-scope.ng-valid-date")).());


        //driver.quit();
    }
}