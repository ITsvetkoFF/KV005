package com.saucelabs;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Roma on 23.10.2014.
 */
public class MapPage implements IMapPage {

    private WebDriver driver;

    public MapPage(WebDriver driver) {
        this.driver = driver;
    }


    @Override
    public void setPosition() {
        JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        }
        js.executeScript("navigator.geolocation.getCurrentPosition = function(success) {" +
                "success({coords: {latitude: 50.649460, longitude: 30.731506}}); }");
    }   // not use

    @Override
    public void setView(double latitude, double longitude, int zoom) {
        JavascriptExecutor script = null;
        if (driver instanceof JavascriptExecutor)
            script = (JavascriptExecutor) driver;
        script.executeScript("var map = document.getElementById(\"map-content\");" +
                "angular.element(map).scope().$parent.$parent.$parent.geoJson._map.setView(["
                + latitude + "," + longitude + "]" + "," + zoom + ");");
    }

    @Override
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

    @Override
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
}
