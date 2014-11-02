package com.saucelabs;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.regex.PatternSyntaxException;

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
                + latitude + "," + longitude + "]" + "," + 14 + ");");

        WebElement container = driver.findElement(By.id("map-content"));
        Dimension point = container.getSize();
        int x = point.getWidth() / 2;
        int y = point.getHeight() / 2;
        Actions builder = new Actions(driver);
        builder.moveToElement(container, x, y - 10).click().build().perform();
    }
	
	public int clickOffsetOfMapCenter(double latitude, double longitude) {
		
		JavascriptExecutor script = null;
		if (driver instanceof JavascriptExecutor)
			script = (JavascriptExecutor) driver;
		script.executeScript("var map = document.getElementById(\"map-content\");" +
                "angular.element(map).scope().$parent.$parent.$parent.geoJson._map.setView(["
                + latitude + "," + longitude + "]" + "," + 14 + ");");
				
		WebElement map = driver.findElement(By.id("map-content"));
        Dimension mapSize = map.getSize();
        int x = mapSize.getWidth() / 2;
        int y = mapSize.getHeight() / 2;
        WebElement problemFrame = driver.findElement(By.xpath("//div[@class='b-addProblem ng-scope']"));
        Dimension problemFrameSize = problemFrame.getSize();
        int problemWidth = problemFrameSize.getWidth();
        Actions point = new Actions(driver);
        point.moveToElement(map, x - (problemWidth / 2), y).click().build().perform();
        return problemWidth / 2;
	}

    public void clickAtProblemOffsetMapCenter(double latitude, double longitude, int offset) {

        JavascriptExecutor script = null;
        if (driver instanceof JavascriptExecutor)
            script = (JavascriptExecutor) driver;
        script.executeScript("var map = document.getElementById(\"map-content\");" +
                "angular.element(map).scope().$parent.$parent.$parent.geoJson._map.setView(["
                + latitude + "," + longitude + "]" + "," + 14 + ");");

        WebElement map = driver.findElement(By.id("map-content"));
        Dimension mapSize = map.getSize();
        int x = mapSize.getWidth() / 2;
        int y = mapSize.getHeight() / 2;
        Actions builder = new Actions(driver);
        builder.moveToElement(map, x - offset, y - 10).click().build().perform();
    }

    @Override
    public String getFilterTitle(int typeNumber) {

        List<WebElement> names = driver.findElements(By.cssSelector(".problem label"));

        return names.get(typeNumber).getAttribute("textContent");
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
            } else {
                if (!"true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            }
        }
    }
    @Override
    public void selectOnlyOneFilter(String typeName) {

        List<WebElement> filtersNames = driver.findElements(By.cssSelector(".problem label[for^='type']"));
        List<WebElement> filtersChecks = driver.findElements(By.xpath("//input[starts-with(@id, 'type')]"));
        String typeId = "";
        for (int i = 0; i < filtersChecks.size(); i++) {
            typeId = filtersChecks.get(i).getAttribute("id");
            if (!typeName.equals(filtersNames.get(i).getText())) {
                if ("true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            } else {
                if (!"true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            }
        }
    }
    //@Override
    public void setAfterDate(String afterDate) {
        List<WebElement> dateFields = driver.findElements(By.cssSelector(".datepicker .form-control"));
        WebElement dateField = dateFields.get(0);
        dateField.clear();
        dateField.sendKeys(afterDate);
    }

    //@Override
    public void setBeforeDate(String beforeDate) {
        List<WebElement> dateFields = driver.findElements(By.cssSelector(".datepicker .form-control"));
        WebElement dateField = dateFields.get(1);
        dateField.clear();
        dateField.sendKeys(beforeDate);
    }

    //@Override
    public void datePickersButtons() {

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
        }
    }

    @Override //not use
    public void selectDate(WebElement datePicker, String year, String month, String day) {

        StringBuilder yearXPath = new StringBuilder("");
        yearXPath.append(".//td/button/span[(text()='");
        yearXPath.append(year);
        yearXPath.append("')]");

        StringBuilder monthXPath = new StringBuilder("");
        monthXPath.append(".//td/button/span[(text()='");
        monthXPath.append(month);
        monthXPath.append("')]");

        StringBuilder dayXPath = new StringBuilder("");
        dayXPath.append(".//td/button/span[(text()='");
        dayXPath.append(day);
        dayXPath.append("')]/..");

        datePicker.findElement(By.xpath(yearXPath.toString())).click();
        datePicker.findElement(By.xpath(monthXPath.toString())).click();
        datePicker.findElement(By.xpath(dayXPath.toString())).click();
    }

    @Override //not use
    public void selectOneDayPeriod(String fullDate) {
        String[] splitDate;
        splitDate = fullDate.split("\\s+");

        List<WebElement> datePickers = driver.findElements(By.cssSelector(".datepicker"));

        for (WebElement datePicker : datePickers) {
            WebElement buttonElement = datePicker.findElement(By.cssSelector(".fa-calendar"));

            buttonElement.click();
            datePicker.findElement(By.className("ng-binding")).click();
            datePicker.findElement(By.className("ng-binding")).click();

            selectDate(datePicker, splitDate[0], splitDate[1], splitDate[2]);
        }
    }
}
//    @Override //not use
//    public void selectTodayButton(WebElement datePicker) {
//        List<WebElement> datePickerButtons = driver.findElements(By.cssSelector(".datepicker"));
//        List<WebElement> inputField = driver.findElements(By.cssSelector(".form-control"));
//        for (WebElement datePickers : datePickerButtons) {
//            WebElement buttonElement = datePickers.findElement(By.cssSelector(".fa-calendar"));
//            buttonElement.click();
//            WebElement todayButton = datePicker.findElement(By.cssSelector("span.btn-group>button.btn-info"));
//            todayButton.click();
//        }
//    }
//
//    @Override //not use
//    public void selectClearButton(WebElement datePicker) {
//        List<WebElement> datePickerButtons = driver.findElements(By.cssSelector(".datepicker"));
//        List<WebElement> inputField = driver.findElements(By.cssSelector(".form-control"));
//        for (WebElement datePickers : datePickerButtons) {
//            WebElement buttonElement = datePickers.findElement(By.cssSelector(".fa-calendar"));
//            buttonElement.click();
//            WebElement selectClearButton = datePicker.findElement(By.cssSelector("span.btn-group>button.btn-info"));
//            selectClearButton.click();
//        }
//    }
//
//    @Override //not use
//    public void closeDatepicker(WebElement datePicker) {
//        List<WebElement> datePickerButtons = driver.findElements(By.cssSelector(".datepicker"));
//        List<WebElement> inputField = driver.findElements(By.cssSelector(".form-control"));
//
//        for (WebElement datePickers : datePickerButtons) {
//            WebElement buttonElement = datePickers.findElement(By.cssSelector(".fa-calendar"));
//            buttonElement.click();
//            WebElement closeDatepicker = datePicker.findElement(By.cssSelector("span.btn-group>button.btn-info"));
//            closeDatepicker.click();
//        }
//    }

