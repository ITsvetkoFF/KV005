package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by onikistc on 23.10.2014.
 */
public class ProblemCommentsTest {
    @Test
    public void addComment() throws InterruptedException, IOException {
        WebDriver driver = new FirefoxDriver();

        ProblemPage problemPage = new ProblemPage(driver);
        AnyPage anyPage = new AnyPage(driver);
        MapPage mapPage = new MapPage(driver);
        AddProblemTest addProblemTest = new AddProblemTest();

        driver.get("http://localhost:8090/#/map");
//        driver.get("http://176.36.11.25/#/map");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        anyPage.logIn("admin@.com", "admin");

        mapPage.clickAtProblemByCoordinate(addProblemTest.latitude, addProblemTest.longitude);
//        driver.get("http://176.36.11.25/#/problem/showProblem/205");
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        int commentsCountBeforeAdding = 0;
        int commentsCountAfterAdding = 0;
        int commentsCountAfterDeleting = 0;

        commentsCountBeforeAdding = driver.findElements(By.className("b-activity__comments-item-content")).size();

        problemPage.addComment();
        driver.navigate().refresh();
        commentsCountAfterAdding = driver.findElements(By.className("b-activity__comments-item-content")).size();

        problemPage.deleteComment();
        driver.navigate().refresh();
        commentsCountAfterDeleting = driver.findElements(By.className("b-activity__comments-item-content")).size();

        Assert.assertTrue(commentsCountAfterAdding == commentsCountBeforeAdding + 1);
        Assert.assertTrue(commentsCountAfterDeleting == commentsCountAfterAdding - 1);

//        Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
        driver.quit();
    }
}
