package com.saucelabs.Tests.LocalTests;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roma on 18.11.2014.
 */
public class AddProblemSikuli {

    @Test
    public void addProblemSikuli() throws IOException {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        driver.get("http://www.ecomap.org/#/map");


        driver.findElement(By.xpath("//button[@class='navbar-brand b-menu__button']")).click();
        List<WebElement> tabs = driver.findElements(By.xpath("//li[@class='ng-isolate-scope']/a/tab-heading/i"));
        tabs.get(1).click();
        tabs.get(1).click();
//----------------------------------------------------------------------------------------------------------------------
        org.openqa.selenium.Dimension dimension = driver.manage().window().getSize();
        org.openqa.selenium.Dimension additionalDimension = new Dimension(dimension.height, dimension.width - 400);

        WebDriver additionalDriver = new FirefoxDriver();
        additionalDriver.manage().window().setSize(additionalDimension);
        additionalDriver.manage().window().setPosition(new Point(0, 0));

        additionalDriver.get("http://prntscr.com/57yeoo");
        additionalDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Dimension koalaDimension = additionalDriver.findElement(By.id("screenshot-image")).getSize();
        Point koalaPosition = additionalDriver.findElement(By.id("screenshot-image")).getLocation();

        Target dropZone = new ImageTarget(new File(".\\resources\\images\\DropZone.jpg"));
        Target koala = new ImageTarget(new URL("http://i.imgur.com/6HuxCqx.jpg"));

//        BufferedImage bigKoala = ImageIO.read(new URL("http://i.imgur.com/6HuxCqx.jpg"));
//        BufferedImage smallKoala = bigKoala.getSubimage(koalaPosition.getX(), koalaPosition.getY(),
//                                                        koalaDimension.getWidth(), koalaDimension.getHeight());
//        Target koala = new ImageTarget(smallKoala);

        ScreenRegion koalaScreen = new DesktopScreenRegion().wait(koala, 20);
        ScreenRegion dropZoneScreen = new DesktopScreenRegion().wait(dropZone, 20);

        Canvas canvas = new DesktopCanvas();
        canvas.addLabel(koalaScreen, "We found Koala!!!");
        canvas.addLabel(dropZoneScreen, "We found DropZone!!!");
        canvas.display(3);

//----------------------------------------------------------------------------------------------------------------------
//        Mouse mouse = new DesktopMouse();
//        mouse.drag((org.sikuli.api.ScreenLocation) koalaScreen);
//        mouse.drop((org.sikuli.api.ScreenLocation) dropZoneScreen);
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
