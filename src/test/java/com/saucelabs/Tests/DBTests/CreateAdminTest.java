package com.saucelabs.Tests.DBTests;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Constant;
import utility.ExcelUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Olya on 11/12/14.
 */
public class CreateAdminTest {
    static WebDriver driver = new FirefoxDriver();
    static AnyPage anyPage = new AnyPage(driver);
    static UserInfoDAO userInfoDB = new UserInfoDAO();
    static CreateNewUserDAO createNewUserDAO = new CreateNewUserDAO();
    static DeleteUserDAO deleteUserDAO = new DeleteUserDAO();
    static ProblemPage problemPage = new ProblemPage(driver);

    @BeforeSuite
    public static void beforeTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URLlocal);
    }

    @DataProvider(name = "AdminUser", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_AdminUserCreateData + Constant.File_AdminUserCreateData, "Sheet1");
    }

    @Test(dataProvider = "AdminUser")
    public void adminUserCreationDBCheck(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        createNewUserDAO.createUser(UserName, UserSurname, UserEmail, UserPassword, UserRoleId);

        Map result = userInfoDB.getInfo("root", "","jdbc:mysql://localhost:3306/enviromap",UserEmail);
        Map<String, String> ExpectedUserData = new HashMap<String, String>();
        ExpectedUserData.put("Name", UserName);
        ExpectedUserData.put("Surname", UserSurname);
        ExpectedUserData.put("Password", userInfoDB.hmacSha1(UserPassword,Constant.HashKey));
        ExpectedUserData.put("UserRoles_Id", UserRoleId);

        Assert.assertEquals(result, ExpectedUserData);
    }

    @Test(dataProvider = "AdminUser", dependsOnMethods = {"adminUserCreationDBCheck"})
    public void adminUserCookiesCheck(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {
        // login as new user
        anyPage.logIn("admin1@gmail.com", "admin");

        Map<String, String> ExpectedUserData = new HashMap<String, String>();
        ExpectedUserData.put("Name", UserName);
        ExpectedUserData.put("Surname", UserSurname);
        ExpectedUserData.put("Email", UserEmail);
        ExpectedUserData.put("UserRole", UserRole);
        Map result = anyPage.getCookiesName();

        Assert.assertEquals(result,ExpectedUserData);
    }

    @Test(dataProvider = "AdminUser", dependsOnMethods = {"adminUserCookiesCheck"})
    public void adminNameCheckAfterLogin(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        String name = anyPage.checkUsernameInRightCorner();
        Assert.assertEquals((UserName + " " + UserSurname).toUpperCase(), name);
    }

    @Test(dataProvider = "AdminUser", dependsOnMethods = {"adminNameCheckAfterLogin"})
    public void addNewResourceAvailability(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        driver.findElement(By.linkText("РЕСУРСИ")).click();
        String add_resource = driver.findElement(By.linkText("ДОДАТИ НОВИЙ РЕСУРС")).getText();

        Assert.assertEquals("ДОДАТИ НОВИЙ РЕСУРС",add_resource);
    }

    @Test(dataProvider = "AdminUser", dependsOnMethods = {"addNewResourceAvailability"})
    public void newsAvailability(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        driver.findElement(By.cssSelector(".fa.fa-weixin")).click();
        anyPage.explicitWaitForElement(5,".b-chat__currentNews>h3");
        String news_title = driver.findElement(By.cssSelector(".b-chat__currentNews>h3")).getText();

        Assert.assertEquals("Новини що відображаються зараз на сайті:",news_title);
    }

    @Test(dataProvider = "AdminUser", dependsOnMethods = {"newsAvailability"})
    public void deleteProblemAvailability(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        problemPage.openProblemById(1);
        anyPage.explicitWaitForButton(5,".b-problems .btn.btn-danger.btn-sm");
        boolean delete_title = driver.findElement(By.cssSelector(".b-problems .btn.btn-danger.btn-sm")).isDisplayed();

        Assert.assertTrue(delete_title);
        anyPage.logOut();
    }

    @Test(dataProvider = "AdminUser", dependsOnMethods = {"deleteProblemAvailability"})
    public void deleteUser(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        deleteUserDAO.deleteUser(UserEmail);
        Map result = userInfoDB.getInfo("root", "","jdbc:mysql://localhost:3306/enviromap",UserEmail);

        Assert.assertTrue(result.isEmpty());
    }

    @AfterSuite
    public static void afterTest() throws Exception{
        driver.quit();
    }

}
