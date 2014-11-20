package com.saucelabs.Tests.LocalTests;

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

        Target target = new ImageTarget(new File(".\\resources\\images\\Drop List.jpg"));
        target.setMinScore(0.8);

        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }

        List<ScreenRegion> screenRegionList = screenRegion.findAll(target);

        for(ScreenRegion screen : screenRegionList) {
            canvas.addBox(screen);
            canvas.addLabel(screen, "We found it!");
        }

        canvas.display(3);
    }
}
