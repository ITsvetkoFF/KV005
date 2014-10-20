package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AnyPage implements IAnyPage {
    private WebDriver driver;
    public AnyPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
        public String getFirstResourceTitleFromMenu() {

            driver.findElement(By.className("fa-caret-down")).click();
            String linkText = driver.findElement(By.xpath("//ul[@id='b-header__resources']/li/a")).getText();

            return linkText;
        }

        @Override
        public String getFirstResourceTitleFromOpenedResource() {

            //driver.findElement(By.className("fa-caret-down")).click();
            driver.findElement(By.xpath("//ul[@id='b-header__resources']/li/a")).click();
            String linkText = driver.findElement(By.tagName("h1")).getText();

            return linkText;
        }



}

