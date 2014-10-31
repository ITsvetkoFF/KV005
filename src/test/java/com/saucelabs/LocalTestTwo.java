package com.saucelabs;

import com.saucelabs.utils.ImageDistanceCalculator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Constant;
import utility.ExcelUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yermek on 31.10.2014.
 */
public class LocalTestTwo {
    @DataProvider(name = "xlsLocalTestData", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_LocalTestData + Constant.File_LocalTestData, "Sheet1");
    }
    @Test(dataProvider = "xlsLocalTestData")
    public void sampleAll(String latitudeString, String longitudeString, String problemTitle,
                          String problemType, String problemDescription, String problemSolution,
                          String imageURLsString, String imageCommentsString,
                          String adminEmail, String adminPassword,
                          String newUserFirstName, String newUserLastName,
                          String newUserEmail, String newUserPassword,
                          String userCommentsString) throws IOException {
        double          latitude        = Double.parseDouble(latitudeString);
        double          longitude       = Double.parseDouble(longitudeString);
        List<String> imageURLs       = Arrays.asList(imageURLsString.split("\n"));
        List<String>    imageComments   = Arrays.asList(imageCommentsString.split("\n"));
        List<String>    userComments    = Arrays.asList(userCommentsString.split("\n"));
        List<String>    receivedURLs;
        List<String>    receivedComments;

        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8090");
        //driver.get("http://176.36.11.25");
        driver.manage().window().maximize();

        AnyPage     anyPage     = new AnyPage(driver);
        AdminPage   adminPage   = new AdminPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);

        anyPage.addProblem(latitude, longitude, problemTitle, problemType, problemDescription, problemSolution,
                imageURLs, imageComments);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        adminPage.logIn(adminEmail, adminPassword);
        Assert.assertTrue(adminPage.checkProblemIsUnderModeration(problemTitle));
        adminPage.approveProblem(problemTitle);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        adminPage.logOut();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        problemPage.clickAtProblemByCoordinate(latitude, longitude);
        Assert.assertEquals(problemPage.getProblemTitle(), problemTitle);
        Assert.assertEquals(problemPage.getProblemType(), problemType);
        Assert.assertEquals(problemPage.getProblemDescription(), problemDescription);
        Assert.assertEquals(problemPage.getProblemPropose(), problemSolution);
        receivedURLs = problemPage.getImageURLs();
        for(int i = 0; i < receivedURLs.size(); i++) {
            Assert.assertTrue(ImageDistanceCalculator.isImagesSimilar(receivedURLs.get(i), imageURLs.get(i)));
        }
        receivedComments = problemPage.getImagesComments();
        for(int i = 0; i < receivedComments.size(); i++){
            Assert.assertTrue(receivedComments.get(i).equals(imageComments.get(i)));
        }
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        problemPage.register(newUserFirstName, newUserLastName, newUserEmail, newUserPassword);
        //problemPage.logIn(newUserEmail, newUserPassword);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        problemPage.addComments(latitude, longitude, userComments);
        problemPage.logOut();

        adminPage.logIn(adminEmail, adminPassword);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        problemPage.deleteComments(latitude, longitude);
        adminPage.logOut();

        driver.quit();
    }
}
