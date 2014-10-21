package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by onikistc on 21.10.2014.
 */
public class ProblemInfoPage {

    private WebDriver driver;
    public ProblemInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProblemTitle() {
        String problemTitle = driver.findElement(By.xpath("//div[@class='b-problem-deatiled-info-title__text']/editproblemtitle")).getText();
        return problemTitle;
    };

    public String getProblemDescription() {
        String problemDescription = driver.findElement(By.xpath("//div[@class='b-problem-deatiled-info-description__content']/editproblemcontent/span")).getAttribute("textContent");
        return problemDescription;
    };



}
