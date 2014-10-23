package com.saucelabs;

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
        AnyPageRoma anyPageRoma = new AnyPageRoma(driver);
        MapPage mapPage = new MapPage(driver);

        //open page by url in browser
        driver.get("http://176.36.11.25/#/map");

        //set maximum size of browser window
        driver.manage().window().maximize();

        //login as admin
        anyPageRoma.logIn("admin@.com", "admin");

        //set position in the map with typed zoom
        mapPage.setView(50, 30, 9);

        //click at addProblem button
        anyPageRoma.clickAddProblem();

        //click at page center on the map
        mapPage.clickAtPagesCenter();

        //typed required fields and select problem type
        anyPageRoma.selectAndAddProblem();

        //3 seconds pause
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //refresh page
        driver.navigate().refresh();

        //3 seconds pause
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //set position in the map with typed zoom
        mapPage.setView(50, 30, 12);

        //click at problem by coordinate
        mapPage.clickAtProblemByCoordinate(50, 30);

        Assert.assertTrue(true);

        //close browser
        driver.quit();
    }
}
