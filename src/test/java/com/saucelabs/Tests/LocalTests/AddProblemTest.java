package com.saucelabs.Tests.LocalTests;

import com.saucelabs.AnyPage;
import com.saucelabs.ProblemPage;
import com.saucelabs.Tests.DAO.AddProblemDAO;
import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
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

    static WebDriver driver = new FirefoxDriver();
    static AnyPage anyPage = new AnyPage(driver);
    static ProblemPage problemPage = new ProblemPage(driver);
    static AddProblemDAO addProblemDAO = new AddProblemDAO();

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
    public List<String> problemComments = Arrays.asList("problemComment1");


    @BeforeSuite
    public void beforeTestSuite() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8090/#/map");
        //driver.get("http://176.36.11.25/#/map");
        anyPage.logIn("admin@.com", "admin");
    }

    @Test
    public void addProblem() throws SQLException, ClassNotFoundException {

        anyPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
                                          problemDescriptionTest, problemProposeTest, imagePath, imageComments);
        driver.navigate().refresh();
        anyPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        Assert.assertEquals(problemPage.getProblemTitle(), problemNameTest);
    }

    @Test(dependsOnMethods = "addProblem")
    public void addProblemComment() throws SQLException, JSONException, ClassNotFoundException {
        problemPage.addComments(latitude, longitude, problemComments);
        int problemId = problemPage.getProblemId(latitude, longitude);
        Assert.assertEquals(problemComments, addProblemDAO.getCommentsFromDB(problemId));
    }

    @AfterSuite
    public void afterTestSuite() {
        driver.quit();
    }
}
