package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import utility.Constant;
import utility.ExcelUtils;

/**
 * Created by Olya on 10/26/14.
 */

public class ResourcesPageTest {

    static WebDriver driver = new FirefoxDriver();
    static ResourcesPage resourcesPage = new ResourcesPage(driver);

    @BeforeSuite
    public static void beforeTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URL);
        resourcesPage.logIn("admin@.com", "admin");
    }

    @DataProvider(name = "testData", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
    }

    @Test(dataProvider = "testData")
    public void createResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception{
        resourcesPage.createResource(ResourceTitle, ResourceAlias, ResourceBody, PlaceToSave);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle), PlaceToSave);
    }

    @Test(dataProvider = "testData")
    public void editResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception{
        resourcesPage.editResource(ResourceTitle, TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle+TextToAdd), PlaceToSave);
    }

    @Test(dataProvider = "testData")
    public void deleteResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception{
        resourcesPage.deleteResource(ResourceTitle+TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle+TextToAdd),null);
    }

    @AfterSuite
    public static void afterTest() throws Exception{
        resourcesPage.logOut();
        driver.quit();
    }

}
