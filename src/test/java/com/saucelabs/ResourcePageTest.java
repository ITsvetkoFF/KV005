package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Olya on 10/23/14.
 */
/*public class ResourcePageTest {
    @Test
    public void runTest() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://localhost:8090/#/map");

        ResourcePage resourcePage = new ResourcePage(driver);

        //resourcePage.logIn("admin@.com", "admin");
        resourcePage.login("admin@.com", "admin");
        String[] possibleItems = new String[]{"У верхньому меню","В розділі \"Ресурси\""};

        String[] newHeaderResources = new String[]{"888","666"};//create in header
        String[] newHeaderResources1 = new String[]{"666","gchgh","888"};//edit in header

        String[] newHeaderResources3 = new String[]{"777","333", "qqq"};//create in list
        String[] newHeaderResources2 = new String[]{"qqq","jhjh","777"};//edit in list

        String[] deleteFromList = new String[]{"qqqTTTT","333"};
        String[] deleteFromHeader = new String[]{"888ABC", "666ABC"};

        resourcePage.createNewResource(newHeaderResources3, possibleItems[1]);
        resourcePage.createNewResource(newHeaderResources, possibleItems[0]);

        resourcePage.editResourceFromList(newHeaderResources2,"TTTT");
        resourcePage.editResourceFromHeader(newHeaderResources1,"ABC");

        resourcePage.deleteResourceFromList(deleteFromList);
        resourcePage.deleteResourceFromHeader(deleteFromHeader);
        driver.quit();
    }

}*/

import utility.Constant;
import utility.ExcelUtils;

/**
 * Created by Volodja on 10/26/14.
 */

public class ResourcePageTest {
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
        //ResourcePage resourcePage = new ResourcePage(driver);
        ResourcesPage resourcesPage = new ResourcesPage();

        //resourcePage.logIn("admin@.com", "admin");
        resourcesPage.login("admin@.com", "admin");
        //SignIn_Action.Execute(driver);
        ResourcesPage.Execute(driver);
        ResourcesPage.editResourceHeader(driver);
        ResourcesPage.editResourceList(driver);
        ResourcesPage.deleteResourceFromHeader(driver);
        ResourcesPage.deleteResourceFromList(driver);
        driver.quit();
        //ExcelUtils.setCellData("Pass", 1, 8);
    }
}
