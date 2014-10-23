package com.saucelabs;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class AnyPageRoma implements IAnyPage {

    private WebDriver driver;
    public String problemName = "addProblemTest";
    public String problemDescription = "addProblemTest";
    public String problemPropose = "addProblemTest";

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

    public void clickAddProblem() {
        driver.findElement(By.xpath("//a[@class='navbar-brand b-menu__button']")).click();
    }

    public void clickAtPagesCenter() {
        int x;
        int y;

        WebElement container = driver.findElement(By.id("map-content"));
        Dimension point = container.getSize();
        x = point.getWidth() / 2;
        y = point.getHeight() / 2;
        Actions builder = new Actions(driver);
        builder.moveToElement(container, x, y).click().build().perform();
    }

    public void setView(double latitude, double longitude, int zoom) {
        JavascriptExecutor script = null;
        if (driver instanceof JavascriptExecutor)
            script = (JavascriptExecutor) driver;
        script.executeScript("var map = document.getElementById(\"map-content\");" +
                "angular.element(map).scope().$parent.$parent.$parent.geoJson._map.setView(["
                + latitude + "," + longitude + "]" + "," + zoom + ");");
    }

    public void clickAtProblemByCoordinate(double latitude, double longitude) {
        JavascriptExecutor script = null;
        if (driver instanceof JavascriptExecutor)
            script = (JavascriptExecutor) driver;
        script.executeScript("var map = document.getElementById(\"map-content\");" +
                "angular.element(map).scope().$parent.$parent.$parent.geoJson._map.setView(["
                + latitude + "," + longitude + "]" + "," + 10 + ");");
        int x;
        int y;

        WebElement container = driver.findElement(By.id("map-content"));
        Dimension point = container.getSize();
        x = point.getWidth() / 2;
        y = point.getHeight() / 2;
        Actions builder = new Actions(driver);
        builder.moveToElement(container, x, y - 10).click().build().perform();
    }

    public void setPosition() {
        JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        }
        js.executeScript("navigator.geolocation.getCurrentPosition = function(success) {" +
                "success({coords: {latitude: 50.649460, longitude: 30.731506}}); }");
    }

    public void selectAndAddProblem() {
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm ng-scope']")).click();
        driver.findElement(By.id("problemName")).sendKeys(problemName);
        driver.findElement(By.cssSelector("#select-field option:nth-child(6)")).click();
        driver.findElement(By.id("description-field")).sendKeys(problemDescription);
        driver.findElement(By.id("proposal-field")).sendKeys(problemPropose);
        driver.findElement(By.xpath("//ul[@class='nav nav-tabs nav-justified']/li[3]")).click();

//        WebElement upload = driver.findElement(By.xpath(".//*[@id='my-awesome-dropzone']/div[1]"));
//        upload.sendKeys("D:\\QA\\TestFiles\\тест1.jpeg");
//
//        StringSelection selection = new StringSelection("D:\\QA\\TestFiles\\тест1.jpeg");
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        driver.findElement(By.id("btn-submit")).click();
    }

    public void close() {
        driver.findElement(By.cssSelector(".close")).click();
    }
}

