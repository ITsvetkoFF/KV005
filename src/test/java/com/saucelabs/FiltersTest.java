package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

/**
 * Created by nklimotc on 21.10.2014.
 */

public class FiltersTest{
    public double latitude;
    public double longitude;

    @Test
    public void checkFilters () throws Exception {

        WebDriver driver = new FirefoxDriver();
        Filters filters = new Filters (driver);
        AnyPage anyPage = new AnyPage(driver);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("http://127.0.0.1:8090/#/map");

        driver.manage().window().maximize();

//        String year = "//td/button/span[(text()='14')]";
//        String month = "//td/button/span[(text()='трав.')]";
//        String day = "//td/button/span[(text()='10')]";

        filters.getFilterTitle();
        filters.clickZoomOut();

        new AlertCloserThread().start();
        anyPage.addProblem(50.2, 30.2, "ProblemFor Проблеми лісів", "Проблеми лісів", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment1");

        new AlertCloserThread().start();
        anyPage.addProblem(50.3, 30.3, "ProblemFor Сміттєзвалища", "Сміттєзвалища", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment2");

//        driver.switchTo().alert().accept();
//        driver.navigate().refresh();
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//        }
//        anyPage.addProblem(50.4, 30.4, "ProblemFor Незаконна забудова", "Незаконна забудова", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment3");
//        driver.navigate().refresh();
//        anyPage.addProblem(50.5, 30.5, "ProblemFor Проблеми водойм", "Проблеми водойм", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment4");
//        driver.navigate().refresh();
//        anyPage.addProblem(50.6, 30.6, "ProblemFor Загрози біорізноманіттю", "Загрози біорізноманіттю", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment5");
//        driver.navigate().refresh();
//        anyPage.addProblem(50.7, 30.7, "ProblemFor Браконьєрство", "Браконьєрство", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment6");
//        driver.navigate().refresh();
//        anyPage.addProblem(50.8, 30.8, "ProblemFor Інші проблеми", "Інші проблеми", "Decsription", "problemProposeTest", "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "comment7");
//        driver.navigate().refresh();

        filters.clickFilter();

        Assert.assertTrue(true);

        filters.datePickers();

                    //driver.quit();
    }
}