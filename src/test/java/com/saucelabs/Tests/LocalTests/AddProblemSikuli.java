package com.saucelabs.Tests.LocalTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roma on 18.11.2014.
 */
public class AddProblemSikuli {

    @Test
    public void addProblemSikuli() {

/*----------------------------------------Selenium code block---------------------------------------------------------*/

//        WebDriver driver = new FirefoxDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        driver.get("http://www.ecomap.org/#/map");
//
//        driver.findElement(By.xpath("//button[@class='navbar-brand b-menu__button']")).click();
//        List<WebElement> tabs = driver.findElements(By.xpath("//li[@class='ng-isolate-scope']/a/tab-heading/i"));
//        tabs.get(1).click();
//        tabs.get(1).click();

/*------------------------------------------Sikuli code block---------------------------------------------------------*/

        DesktopKeyboard keyboard = new DesktopKeyboard();
        Mouse mouse = new DesktopMouse();
        Canvas canvas = new DesktopCanvas();
        ScreenRegion screenRegion = new DesktopScreenRegion();

        keyboard.keyDown(KeyEvent.VK_WINDOWS);
        keyboard.keyDown(KeyEvent.VK_E);
        keyboard.keyUp(KeyEvent.VK_E);
        keyboard.keyUp(KeyEvent.VK_WINDOWS);

        Target pictures = new ImageTarget(new File(".\\resources\\images\\Pictures.jpg"));
        Target samplePictures = new ImageTarget(new File(".\\resources\\images\\Sample Pictures.jpg"));
        Target path = new ImageTarget(new File(".\\resources\\images\\Path.jpg"));
        Target explorer = new ImageTarget(new File(".\\resources\\images\\Explorer.jpg"));

        explorer.setMinScore(0.7);
        List<ScreenRegion> screenRegionList = screenRegion.findAll(explorer);

        for(ScreenRegion screen : screenRegionList) {
            canvas.addBox(screen);
            canvas.addLabel(screen, "We found it!");
        }

        canvas.display(3);
    }
}
