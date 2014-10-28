package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import utility.Constant;
import utility.ExcelUtils;

/**
 * Created by Olya on 10/26/14.
 */

public class ResourcesPageTest {
    private  static WebDriver driver = null;


    @Test
    public void test() throws Exception{
        ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
        //driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.get(Constant.URL);
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URL);

        ResourcesPage resourcesPage = new ResourcesPage(driver);
        resourcesPage.logIn("admin@.com", "admin");
        resourcesPage.CreateResource();
        resourcesPage.editResourceHeader();
        resourcesPage.editResourceList();
        resourcesPage.deleteResourceFromHeader();
        resourcesPage.deleteResourceFromList();
        resourcesPage.logOut();
        driver.quit();
        //ExcelUtils.setCellData("Pass", 1, 8);
    }
}
