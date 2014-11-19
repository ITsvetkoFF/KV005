package com.saucelabs.Tests.LocalTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;
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
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://www.ecomap.org/#/map");

        driver.findElement(By.xpath("//button[@class='navbar-brand b-menu__button']")).click();
        List<WebElement> tabs = driver.findElements(By.xpath("//li[@class='ng-isolate-scope']/a/tab-heading/i"));
        tabs.get(1).click();
        tabs.get(1).click();
//----------------------------------------------------------------------------------------------------------------------
//        org.openqa.selenium.Dimension dimension = driver.manage().window().getSize();
//        org.openqa.selenium.Dimension additionalDimension = new Dimension(dimension.height, dimension.width - 400);
//
//        WebDriver additionalDriver = new FirefoxDriver();
//        additionalDriver.manage().window().setSize(additionalDimension);
//        additionalDriver.manage().window().setPosition(new Point(0, 0));
//
//        additionalDriver.get("http://prntscr.com/57yeoo");
//
//        Target dropZone = new ImageTarget(new File(".\\resources\\images\\DropZone.jpg"));
//        Target koala = new ImageTarget(new File(".\\resources\\images\\KoalaScreen.jpg"));
//
//        ScreenRegion dropZoneScreen = new DesktopScreenRegion().wait(dropZone, 20);
//        ScreenRegion koalaScreen = new DesktopScreenRegion().wait(koala, 20);
//
//        Canvas canvas = new DesktopCanvas();
//        canvas.addLabel(koalaScreen, "We found Koala!!!");
//        canvas.addLabel(dropZoneScreen, "We found DropZone!!!");
//        canvas.display(3);
//
//        Mouse mouse = new DesktopMouse();
//        mouse.drag(koalaScreen.getCenter());
//        mouse.drop(dropZoneScreen.getCenter());

        DesktopKeyboard keyboard = new DesktopKeyboard();
        keyboard.keyDown(KeyEvent.VK_WINDOWS);
        keyboard.keyDown(KeyEvent.VK_E);
        keyboard.keyUp(KeyEvent.VK_E);
        keyboard.keyUp(KeyEvent.VK_WINDOWS);

//
//        additionalDriver.navigate().to("http://prntscr.com/57yflf");
//        ImageTarget desert = new ImageTarget(new URL("http://prntscr.com/57yflf"));
//        ScreenRegion desertScreen = new DesktopScreenRegion().wait(desert, 5);

//----------------------------------------------------------------------------------------------------------------------
//        driver.findElement(By.xpath("//div[contains(@class,'dz-clickable')]/span")).click();
//        ImageTarget target1 = new ImageTarget(new File(".\\resources\\images\\ImagePathField.jpg"));
//        ImageTarget target2 = new ImageTarget(new File(".\\resources\\images\\OpenButton.jpg"));

//        ScreenRegion screenRegion1 = new DesktopScreenRegion().wait(target1, 5);
//        ScreenRegion screenRegion2 = new DesktopScreenRegion().wait(target2, 5);
//
//        Canvas canvas = new DesktopCanvas();
//        canvas.addBox(screenRegion1);
//        canvas.addBox(screenRegion2);
//        canvas.display(3);
//
//        Mouse mouse = new DesktopMouse();
//        Keyboard keyboard = new DesktopKeyboard();
//
//        mouse.click(screenRegion1.getCenter());
//        keyboard.paste("pam-pam");
//
//        mouse.click(screenRegion2.getCenter());
    }
}
