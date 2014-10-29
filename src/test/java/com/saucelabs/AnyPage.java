package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class AnyPage extends MapPage implements IAnyPage {

    private WebDriver driver;

    public AnyPage(WebDriver driver) {
        super(driver);
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
        result = driver.findElement(By.xpath("(//a[contains(@class, 'b-menu__button')])[3]")).getText();
        return result;
    }

    @Override
    public void addProblem(double latitude, double longitude, String problemName, String problemType, String problemDescription, String problemPropose, List<String> imageUrls, List<String> imageComments) {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        setView(latitude, longitude, 9);

        driver.findElement(By.xpath("//*[@class='navbar-brand b-menu__button']")).click();

        clickAtPagesCenter();

        driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm ng-scope']")).click();
        driver.findElement(By.id("problemName")).sendKeys(problemName);

        List<WebElement> elements = driver.findElements(By.cssSelector("#select-field option"));
        for (WebElement element: elements) {
            if (problemType.equals(element.getText()))
            element.click();
        }

        driver.findElement(By.id("description-field")).sendKeys(problemDescription);
        driver.findElement(By.id("proposal-field")).sendKeys(problemPropose);
        driver.findElement(By.xpath("//ul[@class='nav nav-tabs nav-justified']/li[3]")).click();

        for (String url: imageUrls) {
            Thread clicker = new FileChooserThread(url);
            clicker.start();
            driver.findElement(By.xpath("//div[contains(@class,'dz-clickable')]/span")).click();
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
            }
            clicker.interrupt();
        }

        List<WebElement> commentElements = driver.findElements(By.cssSelector("textarea.comment_field"));
        int i = 0;
        for (WebElement element: commentElements) {
            element.sendKeys(imageComments.get(i));
            i++;
        }

        Thread closeAlert = new AlertCloserThread();
        closeAlert.start();
        driver.findElement(By.id("btn-submit")).click();
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
        }
        closeAlert.interrupt();
    }
}