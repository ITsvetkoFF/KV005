package com.saucelabs.Tests.DBTests;

import com.google.common.base.Utf8;
import com.saucelabs.AnyPage;
import com.saucelabs.MapPage;
import com.saucelabs.ProblemPage;
import com.saucelabs.ResourcesPage;
import com.saucelabs.Tests.DAO.CreateNewUserDAO;
import com.saucelabs.Tests.DAO.DeleteUserDAO;
import com.saucelabs.Tests.DAO.UserInfoDAO;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Constant;
import utility.ExcelUtils;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Created by Olya on 11/3/14.
 */
public class CreateUserTest {

    static WebDriver driver = new FirefoxDriver();
    static ResourcesPage resourcesPage = new ResourcesPage(driver);
    static AnyPage anyPage = new AnyPage(driver);
    static UserInfoDAO userInfoDB = new UserInfoDAO();
    static DeleteUserDAO deleteUserDAO = new DeleteUserDAO();

    @BeforeSuite
    public static void beforeTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URLlocal);
    }

    @DataProvider(name = "SimpleUser", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_SimpleUserCreateData + Constant.File_SimpleUserCreateData, "Sheet1");
    }

    @Test(dataProvider = "SimpleUser")
    public void userRegistrationDBCheck(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        anyPage.register(UserName, UserSurname, UserEmail, UserPassword);

        Map result = userInfoDB.getInfo("root", "root","jdbc:mysql://localhost:3306/enviromap",UserEmail);
        Map<String, String> ExpectedUserData = new HashMap<String, String>();
        ExpectedUserData.put("Name", UserName);
        ExpectedUserData.put("Surname", UserSurname);
        ExpectedUserData.put("Password", userInfoDB.hmacSha1(UserPassword,Constant.HashKey));
        ExpectedUserData.put("UserRoles_Id", UserRoleId);

        Assert.assertEquals(result, ExpectedUserData);
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"userRegistrationDBCheck"})
    public void userCookiesCheck(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        Map<String, String> ExpectedUserData = new HashMap<String, String>();
        ExpectedUserData.put("Name", UserName);
        ExpectedUserData.put("Surname", UserSurname);
        ExpectedUserData.put("Email", UserEmail);
        ExpectedUserData.put("UserRole", UserRole);
        Map result = anyPage.getCookiesName();

        Assert.assertEquals(result,ExpectedUserData);
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"userCookiesCheck"})
    public void userNameCheckAfterLogin(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        String name = anyPage.checkUsernameInRightCorner();
        Assert.assertEquals((UserName + " " + UserSurname).toUpperCase(), name);
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"userNameCheckAfterLogin"})
    public void userAddNewResourceAvailability(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        Assert.assertEquals(resourcesPage.existResource("ДОДАТИ НОВИЙ РЕСУРС"), null);
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"userAddNewResourceAvailability"})
    public void userNewsAvailability(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        Assert.assertEquals(anyPage.checkNewsAvailability(),false);
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"userNewsAvailability"})
    public void changePassword(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        anyPage.changePassword(UserPassword,"testpassword");
        Map result = userInfoDB.getInfo("root", "root","jdbc:mysql://localhost:3306/enviromap",UserEmail);
        Map<String, String> ExpectedUserData = new HashMap<String, String>();
        ExpectedUserData.put("Name", UserName);
        ExpectedUserData.put("Surname", UserSurname);
        ExpectedUserData.put("Password", userInfoDB.hmacSha1("testpassword",Constant.HashKey));
        ExpectedUserData.put("UserRoles_Id", UserRoleId);

        Assert.assertEquals(result, ExpectedUserData);
        anyPage.logOut();
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"changePassword"})
    public void deleteUser(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        deleteUserDAO.deleteUser(UserEmail);
        Map result = userInfoDB.getInfo("root", "root","jdbc:mysql://localhost:3306/enviromap",UserEmail);

        Assert.assertTrue(result.isEmpty());
    }

    @AfterSuite
    public static void afterTest() throws Exception{
        driver.quit();
    }
}

