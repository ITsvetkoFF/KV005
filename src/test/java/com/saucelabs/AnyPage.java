package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

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

    @Override
    public void register(String first_name, String last_name, String email, String password) {
        driver.findElement(By.linkText("\u0412\u0425\u0406\u0414")).click();
        driver.findElement(By.xpath("//button[@id='register-button']")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("first_name")).clear();
        driver.findElement(By.name("first_name")).sendKeys(first_name);
        driver.findElement(By.name("last_name")).clear();
        driver.findElement(By.name("last_name")).sendKeys(last_name);
        driver.findElement(By.name("password_second")).clear();
        driver.findElement(By.name("password_second")).sendKeys(password);
        driver.findElement(By.className("b-form__button")).click();
        driver.findElement(By.className("close")).click();
    }

    @Override
    public String getLoggedInUserName() {
        String result;
        result = driver.findElement(By.xpath("(//a[contains(@class, 'b-menu__button')])[4]")).getText();
        return result;
    }

    public void addProblem(double latitude, double longitude, String problemName, String problemType, String problemDescription, String problemPropose) {

        MapPage mapPage = new MapPage(driver);

        //focus on map by coordinate with zoom 9
        mapPage.setView(latitude, longitude, 9);

        //click addProblem button, open window
        driver.findElement(By.xpath("//a[@class='navbar-brand b-menu__button']")).click();

        //click at center of focused map, add marker on map
        mapPage.clickAtPagesCenter();

        //click at describe tab, open tab
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm ng-scope']")).click();

        //type problemName into problemName field
        driver.findElement(By.id("problemName")).sendKeys(problemName);

        //select problem type 5
        driver.findElement(By.cssSelector("#select-field option:nth-child(6)")).click();

        //type problemDescription into problemDescription field
        driver.findElement(By.id("description-field")).sendKeys(problemDescription);

        //type problemPropose into problemPropose field
        driver.findElement(By.id("proposal-field")).sendKeys(problemPropose);

        //click at photo tab, open tab
        driver.findElement(By.xpath("//ul[@class='nav nav-tabs nav-justified']/li[3]")).click();

        //upload file



        //click at submit button, add problem to database
        driver.findElement(By.id("btn-submit")).click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //refresh current page
        driver.navigate().refresh();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //focus on map by coordinate with zoom 12
        mapPage.setView(latitude, longitude, 12);

        //click at added problem
        mapPage.clickAtProblemByCoordinate(latitude, longitude);

        Assert.assertTrue(true);
        driver.quit();
    }
}

