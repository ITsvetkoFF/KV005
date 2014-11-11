package com.saucelabs.Tests.LocalTests;

import com.saucelabs.AnyPage;
import com.saucelabs.ProblemPage;
import com.saucelabs.Tests.DAO.AddProblemDAO;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roma on 21.10.2014.
 */
public class AddProblemTest {

    public static final By COMMENT_PROBLEM_TAB = By.xpath("//ul[@class='nav nav-tabs nav-justified']/li[2]");
    public static final By PROBLEM_COMMENT_FIELD = By.xpath("//textarea[@class='form-control ng-pristine ng-valid']");
    public static final By ADD_PROBLEM_COMMENT_SUBMIT_BUTTON = By.xpath("//a[@class='btn btn-default']");
    public double latitude = 50.1;
    public double longitude = 30.1;
    public String problemNameTest = "problem1";
    public String problemTypeTest = "Загрози біорізноманіттю";
    public String problemDescriptionTest = "description1";
    public String problemProposeTest = "propose1";
    public List<String> imageUrls = Arrays.asList("http://i.imgur.com/HHXCVbs.jpg", "http://i.imgur.com/1K6AdCH.jpg");
    public List<String> imagePath = Arrays.asList("C:\\Users\\Public\\Pictures\\Sample Pictures\\desert.jpg",
                                                  "C:\\Users\\Public\\Pictures\\Sample Pictures\\koala.jpg");
    public List<String> imageComments = Arrays.asList("imageComment1", "imageComment2");

    //@Test
    public void addProblem() throws SQLException, ClassNotFoundException {

        WebDriver driver = new FirefoxDriver();
        AnyPage anyPage = new AnyPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8090/#/map");
        anyPage.logIn("admin@.com", "admin");
        anyPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
                                          problemDescriptionTest, problemProposeTest, imagePath, imageComments);
        driver.navigate().refresh();
        anyPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        Assert.assertEquals(problemPage.getProblemTitle(), problemNameTest);
        driver.quit();
    }

    public void addProblemComment() {

        WebDriver driver = new FirefoxDriver();
        AnyPage anyPage = new AnyPage(driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8090/#/map");
        anyPage.logIn("admin@.com", "admin");
        anyPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.findElement(COMMENT_PROBLEM_TAB).click();
        driver.findElement(PROBLEM_COMMENT_FIELD).sendKeys("problemComment1");
        driver.findElement(ADD_PROBLEM_COMMENT_SUBMIT_BUTTON).click();
    }

    @Test   //use only with previous test
    public void commentUIEqualsDB() throws SQLException, ClassNotFoundException, JSONException {

        WebDriver driver = new FirefoxDriver();
        AnyPage anyPage = new AnyPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);
        AddProblemDAO addProblemDAO = new AddProblemDAO();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8090/#/map");
        anyPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        List<String> commentsUI = problemPage.getComments();
        List<String> commentsDB = addProblemDAO.getCommentsFromDB(problemPage.getProblemId(latitude, longitude));
        int commentsCount = commentsUI.size();
        for (int i = 0; i < commentsCount; i++) {
            Assert.assertEquals(commentsUI.get(i), commentsDB.get(i));
        }
    }
}
