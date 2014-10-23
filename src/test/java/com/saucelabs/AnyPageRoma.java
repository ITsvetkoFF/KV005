package com.saucelabs;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class AnyPageRoma implements IAnyPageRoma {

    private WebDriver driver;
    public String problemName = "addProblemTest";
    public String problemDescription = "addProblemTest";
    public String problemPropose = "addProblemTest";

    public AnyPageRoma(WebDriver driver) {
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

    public void selectAndAddProblem() {
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm ng-scope']")).click();
        driver.findElement(By.id("problemName")).sendKeys(problemName);
        driver.findElement(By.cssSelector("#select-field option:nth-child(6)")).click();
        driver.findElement(By.id("description-field")).sendKeys(problemDescription);
        driver.findElement(By.id("proposal-field")).sendKeys(problemPropose);
        driver.findElement(By.xpath("//ul[@class='nav nav-tabs nav-justified']/li[3]")).click();
        driver.findElement(By.id("my-awesome-dropzone")).click();

//        try {
//        StringSelection selection = new StringSelection("D:\\QA\\TestFiles\\тест1.jpeg");
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
//
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//            robot.keyPress(KeyEvent.VK_CONTROL);
//            robot.keyPress(KeyEvent.VK_V);
//            robot.keyRelease(KeyEvent.VK_V);
//            robot.keyRelease(KeyEvent.VK_CONTROL);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//        } catch (Exception e) {
//        }


        driver.findElement(By.id("btn-submit")).click();
    }
}

