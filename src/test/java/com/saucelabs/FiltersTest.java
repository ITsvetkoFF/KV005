package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by nklimotc on 21.10.2014.
 */

public class FiltersTest{
    @Test
    public void checkFilters () throws Exception {

        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("http://127.0.0.1:8090/#/map");

        String year = "//td/button/span[(text()='14')]";
        String month = "//td/button/span[(text()='трав.')]";
        String day = "//td/button/span[(text()='10')]";

        Filters filters = new Filters (driver);
        //filters.getFilterTitle();
        filters.clickFilter();
        filters.datePickers();
                    //driver.quit();
    }
}