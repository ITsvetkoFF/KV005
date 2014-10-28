package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by onikistc on 21.10.2014.
 */
public class ProblemPage {
    public String comment = "New comment";

    private WebDriver driver;
    public ProblemPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProblemType() {
        String problemIconSRC = null;
        try {
            problemIconSRC = driver.findElement(By.xpath("//img[@class='b-problem-deatiled-info-title__icon']")).getAttribute("ng-src");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public String getImageComment() {
        return  driver.findElement(By.className("")).getText();
    }

    public void addComment() {
        driver.findElement(By.xpath("//div[@class='b-problem-tab ng-isolate-scope']/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//div[@class='b-activity__input-field']/textarea")).sendKeys(comment);
        driver.findElement(By.xpath("//button[@class='b-activity-comment__btn']")).click();
    }

    public void deleteComment() {
        driver.findElement(By.xpath("//div[@class='b-problem-tab ng-isolate-scope']/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//div[@class='b-activity__comments']/div[1]/div[2]/span[2]/i")).click();
    }
}
