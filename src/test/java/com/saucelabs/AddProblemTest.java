package com.saucelabs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tanya on 21.10.2014.
 */
public class AddProblemTest {

    @Test
    public void addProblemTest() {

        WebDriver driver = new FirefoxDriver();
        AnyPage anyPage = new AnyPage(driver);


//        driver.get("http://176.36.11.25/#/map");
        driver.get("http://localhost:8090/#/map");

        driver.manage().window().maximize();

        anyPage.logIn("admin@.com", "admin");

        anyPage.setView(anyPage.latitude, anyPage.longitude, 9);

        anyPage.clickAddProblem();

        anyPage.clickAtPagesCenter();

        anyPage.selectAndAddProblem();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.navigate().refresh();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        anyPage.setView(anyPage.latitude, anyPage.longitude, 12);

        anyPage.clickAtProblemByCoordinate(anyPage.latitude, anyPage.longitude);

        //anyPage.clickAtPagesCenter();

        Assert.assertTrue(true);
        driver.quit();
    }
}
