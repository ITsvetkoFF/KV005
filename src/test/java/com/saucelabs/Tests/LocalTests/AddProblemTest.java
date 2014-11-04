package com.saucelabs.Tests.LocalTests;

import com.saucelabs.AnyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

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
    public List<String> imageComments = Arrays.asList("comment1", "comment2");

    @Test
    public void addProblemTest() {

        WebDriver driver = new FirefoxDriver();
        AnyPage anyPage = new AnyPage(driver);
        driver.get("http://localhost:8090/#/map");
        driver.manage().window().maximize();
        anyPage.logIn("admin@.com", "admin");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        int offset = anyPage.addProblemOffsetPageCenter(latitude, longitude, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest,
                imageUrls, imageComments);

        driver.navigate().refresh();
        anyPage.clickAtProblemOffsetMapCenter(latitude, longitude, offset);
        Assert.assertTrue(true);
        driver.quit();
    }

    //@Test
    public void jDBCSample() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/enviromap", "root", "root");

            if (connection == null) {
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from problems;");

            while (resultSet.next()) {
                System.out.println(resultSet.getRow() + ". " + resultSet.getString("title"));
            }

            statement.close();

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
