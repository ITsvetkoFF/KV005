package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by onikistc on 21.10.2014.
 */
public class ProblemPage extends AnyPage{

    private WebDriver driver;
    public ProblemPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getProblemType() {
        String problemIconSRC;
            problemIconSRC = driver.findElement(By.xpath("//img[@class='b-problem-deatiled-info-title__icon']")).getAttribute("ng-src");
        String problemType = null;
        switch(problemIconSRC) {
            case "images/markers/1.png":
                problemType = "Проблеми лісів";
                break;
            case "images/markers/2.png":
                problemType = "Сміттєзвалища";
                break;
            case "images/markers/3.png":
                problemType = "Незаконна забудова";
                break;
            case "images/markers/4.png":
                problemType = "Проблеми водойм";
                break;
            case "images/markers/5.png":
                problemType = "Загрози біорізноманіттю";
                break;
            case "images/markers/6.png":
                problemType = "Браконьєрство";
                break;
            case "images/markers/7.png":
                problemType = "Інші проблеми";
                break;
        }
        return problemType;
    }

    public String getProblemTitle() {
        return driver.findElement(By.xpath("//div[@class='b-problem-deatiled-info-title__text']/editproblemtitle")).getText();
    }

    public String getProblemDescription() {
        return driver.findElement(By.xpath("//div[@class='b-problem-deatiled-info-description__content']/editproblemcontent/span")).getAttribute("textContent");
    }

    public String getProblemPropose() {
        return driver.findElement(By.xpath("//div[@class='b-problem-deatiled-info-description__content']/editproblemproposal/span")).getAttribute("textContent");
    }

    public List<String> getImageURLs(){
        ProblemTest problemTest = new ProblemTest();
        int imagesAmount = problemTest.imageUrls.size();
        List<String> imageUrls = new ArrayList<>();
        for(int i = 1; i <= imagesAmount; i++) {
            imageUrls.add("http://localhost:8090/" + driver.findElement(By.xpath("//div[@class='b-problem-deatiled-info-description-photos']/div[" + i + "]/img")).getAttribute("ng-src"));
        }
        return imageUrls;
    }

    public List<String> getImagesComments() {
        driver.findElement(By.xpath("//div[@class='b-problem-deatiled-info-description-photos']/div[1]/img")).click();
        ProblemTest problemTest = new ProblemTest();
        int commentsAmount = problemTest.imageComments.size();
        List<String> comments = new ArrayList<>();
        for(int i = 1; i <= commentsAmount; i++){
            comments.add(driver.findElement(By.xpath(".//div[@class='container slider']/div/ul/li[" + i + "]/div")).getAttribute("textContent"));
            if (i < commentsAmount) {
                driver.findElement(By.xpath("//div[@class='rn-carousel-controls ng-isolate-scope']/span[@ng-click='next()']")).click();
            }
        }
        return comments;
    }

    public void addComments(double latitude, double longitude, List<String> comments) {
        clickAtProblemByCoordinate(latitude, longitude);
        driver.findElement(By.xpath("//div[@class='b-problem-tab ng-isolate-scope']/ul/li[2]/a")).click();
        for(String comment : comments) {
            driver.findElement(By.xpath("//div[@class='b-activity__input-field']/textarea")).sendKeys(comment);
            driver.findElement(By.xpath("//button[@class='b-activity-comment__btn']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }

    public void deleteComments(double latitude, double longitude) {
        clickAtProblemByCoordinate(latitude, longitude);
        driver.findElement(By.xpath("//div[@class='b-problem-tab ng-isolate-scope']/ul/li[2]/a")).click();

        List<WebElement> foundActivities = driver.findElements(By.className("b-activity__comments-item"));
        for(WebElement activity : foundActivities) {
            if (activity.findElements(By.xpath(".//i[@class='fa fa-weixin b-activity__comments-item-image']")).size() == 1 ) {
                driver.findElement(By.xpath(".//div[2]/span[2]/i")).click();
            }
        }
    }

    public List<String> getComments() {
        driver.findElement(By.xpath("//div[@class='b-problem-tab ng-isolate-scope']/ul/li[2]/a")).click();
        List<String> comments = new ArrayList<>();
//        int commentsAmount = driver.findElements(By.className("b-activity__comments-item-content")).size() - 1;
        int commentsAmount = driver.findElements(By.xpath("//div[@class='b-activity__comments-item']/i[@class='fa fa-weixin b-activity__comments-item-image']")).size();
        for(int i = 1; i <= commentsAmount; i++) {
            comments.add(0, driver.findElement(By.xpath("//div[@class='b-activity__comments']/div[" + i + "]/div[2]/span[2]")).getAttribute("textContent"));
        }
        return comments;
    }
}





















