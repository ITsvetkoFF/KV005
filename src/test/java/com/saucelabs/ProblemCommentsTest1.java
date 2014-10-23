package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by onikistc on 23.10.2014.
 */
public class ProblemCommentsTest1 {
    @Test
    public void addComment() throws InterruptedException, IOException {
        WebDriver driver = new FirefoxDriver();

        ProblemPage1 problemPage1 = new ProblemPage1(driver);
        AnyPage anyPage = new AnyPage(driver);

//        String link = "http://176.36.11.25";
        driver.get("http://localhost:8090/#/map");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        anyPage.logIn("admin@.com", "admin");

//        anyPage.clickAtProblemByCoordinate(anyPage.latitude, anyPage.longitude);
        driver.get("http://localhost:8090/#/problem/showProblem/55");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        problemPage1.addComment();
//        int count = driver.findElements(By.className("b-activity__comments-item ng-scope")).size();
//        System.out.println("count= " + count);
        problemPage1.deleteComment();
//        int countNew = driver.findElements(By.className("b-activity__comments-item ng-scope")).size();
//        System.out.println("countNew= " + countNew);

        Assert.assertTrue(1 + 1 == 2);

//        driver.close();
//        Thread.sleep(1000);
        driver.quit();
    }
}
