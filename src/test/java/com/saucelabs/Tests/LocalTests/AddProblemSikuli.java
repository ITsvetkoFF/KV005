package com.saucelabs.Tests.LocalTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roma on 18.11.2014.
 */
public class AddProblemSikuli {

    @Test
    public void addProblemSikuli() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.ecomap.org/#/map");


        driver.findElement(By.xpath("//button[@class='navbar-brand b-menu__button']")).click();
        List<WebElement> tabs = driver.findElements(By.xpath("//li[@class='ng-isolate-scope']/a/tab-heading/i"));
        tabs.get(1).click();
        tabs.get(1).click();
        driver.findElement(By.xpath("//div[contains(@class,'dz-clickable')]/span")).click();

        ImageTarget target1 = new ImageTarget(new File(".\\resources\\images\\ImagePathField.jpg"));
        ImageTarget target2 = new ImageTarget(new File(".\\resources\\images\\OpenButton.jpg"));

        ScreenRegion screenRegion1 = new DesktopScreenRegion().wait(target1, 5);
        ScreenRegion screenRegion2 = new DesktopScreenRegion().wait(target2, 5);

        Canvas canvas = new DesktopCanvas();
        canvas.addBox(screenRegion1);
        canvas.addBox(screenRegion2);
        canvas.display(3);

        Mouse mouse = new DesktopMouse();
        Keyboard keyboard = new DesktopKeyboard();

        mouse.click(screenRegion1.getCenter());
        keyboard.paste("pam-pam");

        mouse.click(screenRegion2.getCenter());
    }
}
