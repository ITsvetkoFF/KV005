package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

            driver.findElement(By.xpath("//ul[@id='b-header__resources']/li/a")).click();
            String linkText = driver.findElement(By.tagName("h1")).getText();

            return linkText;
        }

        @Override
        public void logIn(String email, String password) {
            driver.findElement(By.linkText("\u0412\u0425\u0406\u0414")).click();
            driver.findElement(By.name("email")).clear();
            driver.findElement(By.name("email")).sendKeys(email);
            driver.findElement(By.name("password")).clear();
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.id("login-button")).click();
        }

        @Override
        public void logOut() {
            driver.findElement(By.className("fa-user")).click();
            driver.findElement(By.linkText("\u0412\u0418\u0419\u0422\u0418")).click();
    }
}

