package com.saucelabs.Tests.DBTests;

import com.saucelabs.AdminPage;
import com.saucelabs.Tests.DAO.DeleteProblemDAO;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.Constant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yermek on 05.11.2014.
 */
public class DeleteProblemTest {
    //@BeforeClass
    @Test
    public void deleteProblemUI() {
        WebDriver driver = new FirefoxDriver();

        driver.get(Constant.URLlocal);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        AdminPage adminPage = new AdminPage(driver);
        adminPage.logIn(Constant.Username, Constant.Password);

        Assert.assertTrue(1 + 1 == 2);
        driver.quit();
    }

    @Test
    public void getProblemDB() throws SQLException, ClassNotFoundException {
        DeleteProblemDAO dao = new DeleteProblemDAO();
        Map<String, String> problemMap = dao.getProblemById(1);
    }

    @Test
    public void getPhotosDB() throws SQLException, ClassNotFoundException {
        DeleteProblemDAO dao = new DeleteProblemDAO();
        ArrayList<Map<String, String>> problemMap = dao.getPhotosByProblemId(191);
    }
}

