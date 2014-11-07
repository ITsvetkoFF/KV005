package com.saucelabs.Tests.LocalTests;

import com.saucelabs.AnyPage;
import com.saucelabs.ProblemPage;
import com.saucelabs.Tests.DAO.AddProblemDAO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roma on 21.10.2014.
 */
public class AddProblemTest {

    public double latitude = 50.1;
    public double longitude = 30.1;
    public String problemNameTest = "problemNameTest";
    public String problemTypeTest = "Загрози біорізноманіттю";
    public String problemDescriptionTest = "problemDescriptionTest";
    public String problemProposeTest = "problemProposeTest";
    public List<String> imageUrls = Arrays.asList("http://i.imgur.com/HHXCVbs.jpg", "http://i.imgur.com/1K6AdCH.jpg");
    public List<String> imagePath = Arrays.asList("C:\\Users\\Public\\Pictures\\Sample Pictures\\desert.jpg",
                                                  "C:\\Users\\Public\\Pictures\\Sample Pictures\\koala.jpg");
    public List<String> imageComments = Arrays.asList("comment1", "comment2");


    @Test
    public void addProblemTest() throws SQLException, ClassNotFoundException {

        WebDriver driver = new FirefoxDriver();
        AnyPage anyPage = new AnyPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);
        AddProblemDAO addProblemDAO = new AddProblemDAO();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8090/#/map");
        anyPage.logIn("admin@.com", "admin");
        anyPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest,
                imagePath, imageComments);
        driver.navigate().refresh();
        anyPage.clickAtProblemByCoordinateVisible(latitude, longitude);

        String problemNameUI = problemPage.getProblemTitle();
        int problemIDUI = problemPage.getProblemId(latitude, longitude);
        driver.quit();

        Map<String, String> problemMap = addProblemDAO.getMap(problemIDUI);
        Assert.assertEquals(problemMap.get("Id"), Integer.toString(problemIDUI));
    }
}
