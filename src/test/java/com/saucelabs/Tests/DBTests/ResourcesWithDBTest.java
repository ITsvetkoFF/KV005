package com.saucelabs.Tests.DBTests;

import com.saucelabs.ResourcesPage;
import com.saucelabs.Tests.DAO.ResourcesDAO;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.ExcelUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.sql.DriverManager.getConnection;

/**
 * Created by nklimotc on 11.11.2014.
 */
public class ResourcesWithDBTest {
    WebDriver driver;
    ResourcesPage resourcesPage;
//    String alias = "AliasForDAO";
//    String title = "Новий ресурс для DAO у верхньому меню";
//    String content = "Контент нового ресурсу для DAO";
//    int isResource = 1;
    public static final String Path_TestData = ".\\resources\\";
    public static final String File_TestDataForResourceAddingToDB = "TestDataForResourceAddingToDB.xlsx";
    String value0 = "У верхньому меню";
    String value1 = "В розділі \"Ресурси\"";

    @BeforeSuite
    public void setUp() {

        this.driver = new FirefoxDriver();
        this.resourcesPage = new ResourcesPage(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://127.0.0.1:8090/#/map");
        resourcesPage.logIn("admin@.com", "admin");
        }

    @AfterSuite
    public void turnDown() {
        this.driver.quit();
    }

    @DataProvider(name = "TestDataForResourceAddingToDB", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Path_TestData + File_TestDataForResourceAddingToDB, "Sheet1");
    }

    @Test (dataProvider = "TestDataForResourceAddingToDB")
    public void addResourceToDB(String alias, String title, String content, String isResource) throws Exception {
        ResourcesDAO dao = new ResourcesDAO();
        List<String> Aliases = new ArrayList<>();
        //Aliases = dao.getAllAliases();

       dao.addResourceToDB(alias, title, content, Integer.parseInt(isResource));

        Assert.assertEquals(resourcesPage.existResource(title), value0);
        }

    //@Test (dataProvider = "TestDataForResourceAddingToDB", dependsOnMethods = {"addResourceToDB"})
    public void editResourceInDB(String newAlias, String newTitle, String newContent, String existedAlias) throws Exception {
        ResourcesDAO dao = new ResourcesDAO();

        dao.editResourceInDB(newAlias, newTitle, newContent, existedAlias);

        Assert.assertEquals(resourcesPage.existResource(newTitle), value0);
        }

   // @Test (dataProvider = "TestDataForResourceAddingToDB", dependsOnMethods = {"editResourceInDB"})
    public void deleteResourceFromDB(String newTitle) throws Exception {
        ResourcesDAO dao = new ResourcesDAO();

        dao.deleteResourceFromDB(newTitle);

        Assert.assertEquals(resourcesPage.existResource(newTitle), null);
        }

}
