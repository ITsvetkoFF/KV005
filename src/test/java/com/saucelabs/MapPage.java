package com.saucelabs;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created by Roma on 23.10.2014.
 */
public class MapPage implements IMapPage {

    private WebDriver driver;

    public MapPage(WebDriver driver) {
        this.driver = driver;
    }


    @Override
    public void setPosition() {    // not use
        JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        }
        js.executeScript("navigator.geolocation.getCurrentPosition = function(success) {" +
                "success({coords: {latitude: 50.649460, longitude: 30.731506}}); }");
    }

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

        WebElement map = driver.findElement(By.id("map-content"));
        Dimension point = map.getSize();
        x = point.getWidth() / 2;
        y = point.getHeight() / 2;
        Actions builder = new Actions(driver);
        builder.moveToElement(map, x, y).clickAndHold().release().build().perform();
    }

    @Override
    public void clickAtProblemByCoordinate (double latitude, double longitude) {
        JavascriptExecutor script = null;
        if (driver instanceof JavascriptExecutor)
            script = (JavascriptExecutor) driver;
        script.executeScript("var map = document.getElementById(\"map-content\");" +
                "angular.element(map).scope().$parent.$parent.$parent.geoJson._map.setView(["
                + latitude + "," + longitude + "]" + "," + 12 + ");");
        int x;
        int y;

        WebElement container = driver.findElement(By.id("map-content"));
        Dimension point = container.getSize();
        x = point.getWidth() / 2;
        y = point.getHeight() / 2;
        Actions builder = new Actions(driver);
        builder.moveToElement(container, x, y - 10).click().build().perform();
    }

    @Override
    public String getFilterTitle(int typeNumber) {

        List<WebElement> names = driver.findElements(By.cssSelector(".problem label"));

        return names.get(typeNumber - 1).getAttribute("textContent");
    }

    @Override
    public void clickZoomOut() {
        WebElement zoomOut = driver.findElement(By.xpath("//a[@title='Zoom out']"));

        do {
            zoomOut.click();
        } while (!zoomOut.getAttribute("class").contains("disabled"));
    }

    @Override
    public void openFiltersBoard() {
        driver.findElement(By.xpath("//div[@class='b-left-side__pointer']")).click();
    }

    @Override
    public void selectAllExceptOneFilter(int typeNumber) throws Exception {

        List<WebElement> filtersNames = driver.findElements(By.cssSelector(".problem label[for^='type']"));
        List<WebElement> filtersChecks = driver.findElements(By.xpath("//input[starts-with(@id, 'type')]"));
        String typeId = "";
//        JavascriptExecutor js = null;
//        if (driver instanceof JavascriptExecutor) {
//            js = (JavascriptExecutor) driver;
//        }

        for (int i = 0; i < filtersChecks.size(); i++) {
            typeId = filtersChecks.get(i).getAttribute("id");
            if (i != typeNumber - 1) {
                if (!"true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
//                js.executeScript("document.getElementById('" + typeId + "').checked = true");
            } else {
                if ("true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
//                js.executeScript("document.getElementById('" + typeId + "').checked = false");
            }
        }
    }

    @Override
    public void selectOnlyOneFilter(int typeNumber) {

        List<WebElement> filtersNames = driver.findElements(By.cssSelector(".problem label[for^='type']"));
        List<WebElement> filtersChecks = driver.findElements(By.xpath("//input[starts-with(@id, 'type')]"));
        String typeId = "";
        for (int i = 0; i < filtersChecks.size(); i++) {
            typeId = filtersChecks.get(i).getAttribute("id");
            if (i != typeNumber - 1) {
                if ("true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            }
            else {
                if (!"true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            }
        }
    }

    @Override
    public void datePickers() {

        List<WebElement> datePickerButtons = driver.findElements(By.cssSelector(".datepicker"));

        for (WebElement datePickers : datePickerButtons) {
            WebElement buttonElement = datePickers.findElement(By.cssSelector(".fa-calendar"));

            buttonElement.click();
            WebElement todayButton = datePickers.findElement(By.cssSelector("span.btn-group>button.btn-info"));
            todayButton.click();

            buttonElement.click();
            WebElement clearButton = datePickers.findElement(By.cssSelector("button.btn-success"));
            clearButton.click();

            buttonElement.click();
            WebElement closeButton = datePickers.findElement(By.cssSelector("button.btn-danger"));
            closeButton.click();

            buttonElement.click();
            datePickers.findElement(By.className("ng-binding")).click();
            datePickers.findElement(By.className("ng-binding")).click();

            datePickers.findElement(By.xpath(".//td/button/span[(text()='14')]")).click();
            datePickers.findElement(By.xpath(".//td/button/span[(text()='трав.')]")).click();
            datePickers.findElement(By.xpath(".//td/button/span[(text()='10')]")).click();
        }

    }
}
