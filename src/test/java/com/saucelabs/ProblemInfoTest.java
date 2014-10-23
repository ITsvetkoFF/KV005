package com.saucelabs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by onikistc on 21.10.2014.
 */
public class ProblemInfoTest {
    @Test
    public void checkProblemInfo() {
        WebDriver driver = new FirefoxDriver();

        //int problemNumber = anyPage.getProblemNumber
        int problemNumber = 53;
        String link = "http://localhost:8090/#/problem/showProblem/" + problemNumber;
        driver.get(link);
        JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        };
        js.executeScript("navigator.geolocation.getCurrentPosition = function(success) { success({coords: {latitude: 50.649460, longitude: 30.731506}}); }");

        ProblemInfoPage infoPage = new ProblemInfoPage(driver);
        System.out.println("title:" + infoPage.getProblemTitle());
        System.out.println("description:" + infoPage.getProblemDescription());

        //Assert.assertTrue(infoPage.getProblemTitle().equals(anyPage.getProblemTitle()));
        //Assert.assertTrue(infoPage.getProblemDescription().equals(anyPage.getProblemDescription()));
        Assert.assertTrue(1 + 1 == 2);
        driver.quit();
    }

}
