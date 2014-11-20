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

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roma on 18.11.2014.
 */
public class AddProblemSikuli {

    @Test
    public void addProblemSikuli() throws IOException {

/*----------------------------------------Selenium code block---------------------------------------------------------*/

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://176.36.11.25/#/map");

        driver.findElement(By.xpath("//button[@class='navbar-brand b-menu__button']")).click();
        List<WebElement> tabs = driver.findElements(By.xpath("//li[@class='ng-isolate-scope']/a/tab-heading/i"));
        tabs.get(1).click();
        tabs.get(1).click();

/*------------------------------------------Sikuli code block---------------------------------------------------------*/

        DesktopKeyboard keyboard = new DesktopKeyboard();
        Mouse mouse = new DesktopMouse();
        Canvas canvas = new DesktopCanvas();
        ScreenRegion screenRegion = new DesktopScreenRegion();

        Target triangle = new ImageTarget(new File(".\\resources\\images\\Drop List.jpg"));
        triangle.setMinScore(0.8);

        Target dropZone = new ImageTarget(new File(".\\resources\\images\\Drop Zone.jpg"));
        dropZone.setMinScore(0.8);

        BufferedImage bigKoala = ImageIO.read(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg"));
        BufferedImage smallKoala = bigKoala.getSubimage(320, 120, 600, 600);

        Target koala = new ImageTarget(new File(".\\resources\\images\\Koala.jpg"));
        koala.setMinScore(0.8);

        keyboard.keyDown(KeyEvent.VK_WINDOWS);
        keyboard.keyDown(KeyEvent.VK_E);
        keyboard.keyUp(KeyEvent.VK_E);
        keyboard.keyUp(KeyEvent.VK_WINDOWS);

        try {
            Thread.sleep(700);
        } catch (Exception e) {
        }

        List<ScreenRegion> screenRegionList = screenRegion.findAll(triangle);
        for(ScreenRegion screen : screenRegionList) {
            canvas.addBox(screen);
        }
        canvas.display(1);
        canvas.clear();
        canvas.addBox(screenRegionList.get(0));
        canvas.addLabel(screenRegionList.get(0), "We get this!");
        canvas.display(1);
        canvas.clear();
        mouse.click(screenRegionList.get(0).getRelativeScreenLocation(20, 0));
        keyboard.paste("C:\\Users\\Public\\Pictures\\Sample Pictures");
        keyboard.keyDown(KeyEvent.VK_ENTER);
        keyboard.keyUp(KeyEvent.VK_ENTER);

        ScreenRegion dropZoneScreenRegion = new DesktopScreenRegion().wait(dropZone, 10);
        canvas.addBox(dropZoneScreenRegion);
        canvas.addLabel(dropZoneScreenRegion, "We found Drop Zone!");
        canvas.display(1);
        canvas.clear();

        canvas.addImage(screenRegionList.get(0).getCenter(), smallKoala);
        canvas.addLabel(screenRegionList.get(0), "           We want choose this nice Koala!");
        canvas.display(4);
        canvas.clear();

        ScreenRegion koalaScreenRegion = new DesktopScreenRegion().wait(koala, 10);
        mouse.drag(koalaScreenRegion.getCenter());
        mouse.drop(dropZoneScreenRegion.getCenter());
    }
}
