package com.saucelabs.Tests.DBTests;

import com.saucelabs.AnyPage;
import com.saucelabs.MapPage;
import com.saucelabs.ResourcesPage;
import com.saucelabs.Tests.DAO.CreateNewUserDAO;
import com.saucelabs.Tests.DAO.UserInfoDAO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utility.Constant;

import java.util.List;
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
    static CreateNewUserDAO createNewUser = new CreateNewUserDAO();

    @BeforeSuite
    public static void beforeTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URLlocal);
        //resourcesPage.logIn("admin@.com", "admin");
    }

    // need to add new excel with test data
    /*@DataProvider(name = "testData1", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestDataLocal, "Sheet1");
    }*/


            //check values in cookies
            /*URLConnection connection1 = new URL("http://localhost:8090/#/map").openConnection();
            List<String> cookies = connection1.getHeaderFields().get("Set-Cookie");
            for (String cookie : cookies) {
                System.out.println(cookie);
            }*/
            //resourcesPage.getCookieUsingCookieHandler();
            //check resource add button
            //Assert.assertEquals(resourcesPage.existResource("ДОДАТИ НОВИЙ РЕСУРС"),null);

            //check news button and click, add button availability and close
            //boolean news_displayed = driver.findElement(By.cssSelector("news_button")).isDisplayed();
            //System.out.println(news_displayed);
            //Assert.assertEquals(news_displayed,false);


    @Test
    public void SimpleUserCreation() throws Exception {
        String[] result = null;

        anyPage.register("newUser", "LastName", "test@gmail.com", "test");
        result = userInfoDB.getInfo("root", "","jdbc:mysql://localhost:3306/enviromap","test@gmail.com");

        Assert.assertEquals(result[0],"newUser");
        Assert.assertEquals(result[1],"LastName");
        Assert.assertEquals(result[2], userInfoDB.hmacSha1("test","qawvAsgn2GRtPww066ShB6cX79ZUAV7KTzXXvNIzkr0IlLnJ"));
        Assert.assertEquals(result[3],"2");

        // login as new user
        //anyPage.logIn("test@gmail.com", "test");
        //check name in right corner
        WebElement alert = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".dropdown-toggle.b-menu__button.ng-binding")));
        String user_name = driver.findElement(By.cssSelector(".dropdown-toggle.b-menu__button.ng-binding")).getText();
        Assert.assertEquals(("newUser"+" "+ "LastName").toUpperCase(),user_name);
        Assert.assertEquals(resourcesPage.existResource("ДОДАТИ НОВИЙ РЕСУРС"),null);

        boolean newsExist = true;
        List<WebElement> resources1 = driver.findElements(By.cssSelector(".navbar-nav>li"));
        for (WebElement listElement : resources1){
            String searchText = listElement.getText();
            if (searchText.equals("НОВИНИ")){
                System.out.println("Simple user sees News menu");
            }
            else {
              newsExist = false;
            }
        }
        Assert.assertEquals(newsExist,false);
        anyPage.logOut();
    }

    @Test
    public void AdminUserCreation() throws Exception {
        String[] result;
        //createNewUser.createUser("newAdmin", "TestSurname", "admin1@gmail.com", "admin", "1");

        result = userInfoDB.getInfo("root", "","jdbc:mysql://localhost:3306/enviromap","admin1@gmail.com");
        Assert.assertEquals(result[0],"newAdmin");
        Assert.assertEquals(result[1],"TestSurname");
        Assert.assertEquals(result[2], userInfoDB.hmacSha1("admin","qawvAsgn2GRtPww066ShB6cX79ZUAV7KTzXXvNIzkr0IlLnJ"));
        Assert.assertEquals(result[2], "admin");
        Assert.assertEquals(result[3],"1");
        // login as new user
        anyPage.logIn("admin1@gmail.com", "admin");
        //check name in right corner
        String user_name = driver.findElement(By.cssSelector(".dropdown-toggle.b-menu__button.ng-binding")).getText();
        Assert.assertEquals(("newAdmin"+" "+ "TestSurname").toUpperCase(),user_name);

        //check resource add button
        driver.findElement(By.linkText("РЕСУРСИ")).click();
        String add_resource = driver.findElement(By.linkText("ДОДАТИ НОВИЙ РЕСУРС")).getText();
        Assert.assertEquals("ДОДАТИ НОВИЙ РЕСУРС",add_resource);

        //check news button and click, add button availability and close
        driver.findElement(By.cssSelector(".fa.fa-weixin")).click();
        String news_title = driver.findElement(By.cssSelector(".b-chat__currentNews>h3")).getText();
        Assert.assertEquals("Новини що відображаються зараз на сайті:",news_title);
        //check delete problem button
        mapPage.clickAtProblemByCoordinateVisible(46.832600, 33.416462);
        String delete_title = driver.findElement(By.linkText("Видалити проблему з бази")).getText();
        Assert.assertEquals(delete_title,"Видалити проблему з бази");
        driver.findElement(By.cssSelector(".close")).click();
        anyPage.logOut();
    }

    @AfterSuite
    public static void afterTest() throws Exception{
        driver.quit();
    }
}

