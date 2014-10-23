package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by onikistc on 21.10.2014.
 */
public class ProblemTest1 {
    @Test
    public void checkProblemInfo() {
        WebDriver driver = new FirefoxDriver();
        ProblemPage1 problemPage1 = new ProblemPage1(driver);
        AnyPage anyPage = new AnyPage(driver);

//        String link = "http://176.36.11.25";
        String link = "http://localhost:8090/#/problem/showProblem/55";
        driver.get(link);
//        driver.manage().window().maximize();

        String title = problemPage1.getProblemTitle();
        String description = problemPage1.getProblemDescription();
        String propose = problemPage1.getProblemPropose();

        System.out.println("title:" + title);
        System.out.println("description:" + description);
        System.out.println("propose:" + propose);

//        anyPage.clickAtProblemByCoordinate(anyPage.latitude, anyPage.longitude);
//        Assert.assertTrue(title.equals(anyPage.problemName));
//        Assert.assertTrue(description.equals(anyPage.problemDescription));
//        Assert.assertTrue(propose.equals(anyPage.problemPropose));
        driver.quit();
    }
}

