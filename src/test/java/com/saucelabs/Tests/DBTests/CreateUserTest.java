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
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Created by Olya on 11/3/14.
 */
public class CreateUserTest {

    static WebDriver driver = new FirefoxDriver();
    static ResourcesPage resourcesPage = new ResourcesPage(driver);
    static AnyPage anyPage = new AnyPage(driver);
    static MapPage mapPage = new MapPage(driver);
    static UserInfoDAO userInfoDB = new UserInfoDAO();
    static CreateNewUserDAO createNewUserDAO = new CreateNewUserDAO();
    static DeleteUserDAO deleteUserDAO = new DeleteUserDAO();
    static ProblemPage problemPage = new ProblemPage(driver);

    @BeforeSuite
    public static void beforeTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URLlocal);
    }

    @DataProvider(name = "SimpleUser", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestDataLocal, "Sheet1");
    }

    @Test(dataProvider = "SimpleUser")
    public void userRegistrationDBCheck(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        String[] result = null;
        //anyPage.register("newUser", "LastName", "test@gmail.com", "test"); UserRoleId=2
        anyPage.register(UserName, UserSurname, UserEmail, UserPassword);
        result = userInfoDB.getInfo("root", "","jdbc:mysql://localhost:3306/enviromap",UserEmail);

        Assert.assertEquals(result[0],UserName);
        Assert.assertEquals(result[1],UserSurname);
        Assert.assertEquals(result[2], userInfoDB.hmacSha1(UserPassword,Constant.HashKey));
        Assert.assertEquals(result[3],UserRoleId);
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"userRegistrationDBCheck"})
    public void userCookiesCheck(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {
        // login as new user UserRole = user
        /*Cookie cookie_name = driver.manage().getCookieNamed("userName");
        Cookie cookie_surname = driver.manage().getCookieNamed("userSurname");
        Cookie cookie_role = driver.manage().getCookieNamed("userRole");
        Cookie cookie_email = driver.manage().getCookieNamed("userEmail");*/

        Assert.assertEquals(anyPage.getCookieName("userName"), UserName);
        Assert.assertEquals(anyPage.getCookieName("userSurname"),UserSurname);
        Assert.assertEquals(anyPage.getCookieName("userRole"),UserRole);
        Assert.assertEquals(anyPage.getCookieName("userEmail"),UserEmail);
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"userCookiesCheck"})
    public void userNameCheckAfterLogin(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        //check name in right corner
        //anyPage.checkUsernameInRightCorner("newUser","LastName");
        /*WebElement alert = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".dropdown-toggle.b-menu__button.ng-binding")));
        String user_name = alert.findElement(By.cssSelector(".dropdown-toggle.b-menu__button.ng-binding")).getText();
        Assert.assertEquals(("newUser"+" "+ "LastName").toUpperCase(),user_name);*/
        //?? should be here assert?? if yes:
        Assert.assertEquals((UserName + " " + UserSurname).toUpperCase(), anyPage.checkUsernameInRightCorner());
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"userNameCheckAfterLogin"})
    public void userAddNewResourceAvailability(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {
        Assert.assertEquals(resourcesPage.existResource("ДОДАТИ НОВИЙ РЕСУРС"), null);
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"userAddNewResourceAvailability"})
    public void userNewsAvailability(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {
        Assert.assertEquals(anyPage.checkNewsAvailability(),false);
        anyPage.logOut();
    }

    @Test(dataProvider = "SimpleUser", dependsOnMethods = {"userNewsAvailability"})
    public void deleteUser(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {
        deleteUserDAO.deleteUser(UserEmail);
        //??
        String[] result = null;
        result = userInfoDB.getInfo("root", "","jdbc:mysql://localhost:3306/enviromap",UserEmail);
        Assert.assertNull(result);
    }

    @Test(dataProvider = "AdminUser")
    public void adminUserCreationDBCheck() throws Exception {
        String[] result;
        createNewUserDAO.createUser("newAdmin", "TestSurname", "admin1@gmail.com", "admin", "1");

        result = userInfoDB.getInfo("root", "","jdbc:mysql://localhost:3306/enviromap","admin1@gmail.com");

        Assert.assertEquals(result[0],"newAdmin");
        Assert.assertEquals(result[1],"TestSurname");
        Assert.assertEquals(result[2], userInfoDB.hmacSha1("admin","qawvAsgn2GRtPww066ShB6cX79ZUAV7KTzXXvNIzkr0IlLnJ"));
        Assert.assertEquals(result[3],"1");
    }

    @Test(dataProvider = "AdminUser", dependsOnMethods = {"adminUserCreationDBCheck"})
    public void adminUserCookiesCheck(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {
        // login as new user
        anyPage.logIn("admin1@gmail.com", "admin");
        //check name in right corner

        Cookie cookie_name = driver.manage().getCookieNamed("userName");
        Cookie cookie_surname = driver.manage().getCookieNamed("userSurname");
        Cookie cookie_role = driver.manage().getCookieNamed("userRole");
        Cookie cookie_email = driver.manage().getCookieNamed("userEmail");
        Assert.assertEquals(cookie_name.getValue(), "newAdmin");
        Assert.assertEquals(cookie_surname.getValue(),"TestSurname");
        Assert.assertEquals(cookie_role.getValue(),"administrator");
        Assert.assertEquals(URLDecoder.decode(cookie_email.getValue(), "UTF-8"),"admin1@gmail.com");

        String user_name = driver.findElement(By.cssSelector(".dropdown-toggle.b-menu__button.ng-binding")).getText();
        Assert.assertEquals(("newAdmin"+" "+ "TestSurname").toUpperCase(),user_name);

        //check resource add button
        driver.findElement(By.linkText("РЕСУРСИ")).click();
        String add_resource = driver.findElement(By.linkText("ДОДАТИ НОВИЙ РЕСУРС")).getText();
        Assert.assertEquals("ДОДАТИ НОВИЙ РЕСУРС",add_resource);

        //check news button and click, add button availability and close
        driver.findElement(By.cssSelector(".fa.fa-weixin")).click();
        //WebElement alert = (new WebDriverWait(driver, 10))
                //.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".b-chat__currentNews>h3")));
        //alert.isDisplayed(); 
        //WebDriverWait wait = new WebDriverWait(driver, 5);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b-chat__currentNews>h3")));
        anyPage.explicitWaitForElement(5,".b-chat__currentNews>h3");
        String news_title = driver.findElement(By.cssSelector(".b-chat__currentNews>h3")).getText();
        Assert.assertEquals("Новини що відображаються зараз на сайті:",news_title);

        //driver.findElement(By.linkText("×")).click();
        problemPage.openProblemById(1);
        //check delete problem button
        //mapPage.clickAtProblemByCoordinateVisible(46.832600, 33.416462);
        //WebDriverWait wait1  = new WebDriverWait(driver, 10);
        //wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".b-problems .btn.btn-danger.btn-sm")));
        anyPage.explicitWaitForButton(5,".b-problems .btn.btn-danger.btn-sm");
        boolean delete_title = driver.findElement(By.cssSelector(".b-problems .btn.btn-danger.btn-sm")).isDisplayed();
        Assert.assertTrue(delete_title);
        driver.findElement(By.linkText("×")).click();
        anyPage.logOut();

        deleteUserDAO.deleteUser("admin1@gmail.com");
    }

    @AfterSuite
    public static void afterTest() throws Exception{
        driver.quit();
    }
}

