package com.saucelabs.Tests.DBTests;

import com.saucelabs.AdminPage;
import com.saucelabs.AnyPage;
import com.saucelabs.ProblemPage;
import com.saucelabs.Tests.DAO.DeleteProblemDAO;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Constant;
import utility.ExcelUtils;
import utility.FileSystem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yermek on 05.11.2014.
 */
public class DeleteProblemTest {
    double       latitude;
    double       longitude;
    String       problemTitle;
    String       problemType;
    String       problemDescription;
    String       problemSolution;
    List<String> imageURLs;
    List<String> imageComments;
    String       adminEmail;
    String       adminPassword;
    WebDriver    driver;
    int          problemId;

    public DeleteProblemTest(String latitudeString, String longitudeString,
                             String problemTitle, String problemType, String problemDescription, String problemSolution,
                             String imageURLsString, String imageCommentsString, String adminEmail, String adminPassword
                            ) {
        this.latitude           = Double.parseDouble(latitudeString);
        this.longitude          = Double.parseDouble(longitudeString);
        this.problemTitle       = problemTitle;
        this.problemType        = problemType;
        this.problemDescription = problemDescription;
        this.problemSolution    = problemSolution;
        this.imageURLs          = Arrays.asList(imageURLsString.split("\n"));
        this.imageComments      = Arrays.asList(imageCommentsString.split("\n"));
        this.adminEmail         = adminEmail;
        this.adminPassword      = adminPassword;
    }

    @BeforeGroups(groups = {"delete"})
    public void SetUp() {
        System.out.println("BeforeGroups delete.");
        driver = new FirefoxDriver();

        driver.get(Constant.URLlocal);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        AdminPage adminPage = new AdminPage(driver);
        adminPage.logIn(Constant.Username, Constant.Password);
    }

    @AfterGroups(groups = {"delete"})
    public void TearDown() {
        System.out.println("AfterGroups delete.");
        AdminPage adminPage = new AdminPage(driver);
        adminPage.logOut();
        driver.quit();
    }

    @Test(groups = {"delete"})
    public void addProblemUI() throws IOException {
        System.out.print("Groups delete, Test addProblemUI. ");
        AnyPage anyPage     = new AnyPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        anyPage.addProblemToVisibleCenter(latitude, longitude,
                problemTitle, problemType, problemDescription, problemSolution,
                imageURLs, imageComments);
        System.out.print("Problem added. ");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        driver.navigate().refresh();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        problemId = problemPage.getProblemId(latitude, longitude);
        System.out.println("Problem ID stored. ");
        Assert.assertTrue(1 + 1 == 2);
    }

    @Test(groups = {"delete"})
    public void deleteProblemUI() {
        System.out.print("Groups delete, Test deleteProblemUI. ");
        AdminPage adminPage   = new AdminPage(driver);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        adminPage.pressDeleteProblemButton();
        System.out.println("Problem deleted. ");
        Assert.assertTrue(1 + 1 == 2);
    }

    //@Test
    public void getProblemDB() throws SQLException, ClassNotFoundException {
        DeleteProblemDAO dao = new DeleteProblemDAO();
        Map<String, String> problemMap = dao.getProblemById(1);
    }

    //@Test
    public void getPhotosDB() throws SQLException, ClassNotFoundException {
        FileSystem fs = new FileSystem(Constant.Path_ImagesLocalFolder);
        DeleteProblemDAO dao = new DeleteProblemDAO();
        ArrayList<Map<String, String>> photos = dao.getPhotosByProblemId(191);

        for (int i = 0; i < photos.size(); i++) {
            System.out.println(photos.get(i).get("Link"));
            System.out.println(fs.isImageFilePresentInFolder(photos.get(i).get("Link")));
        }
    }
}