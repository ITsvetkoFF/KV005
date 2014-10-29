package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Created by nklimotc on 21.10.2014.
 */

public class Filters {

    public Filters(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    AnyPage anyPage = new AnyPage(driver);
    public double latitude;
    public double longitude;

    public String getFilterTitle() {

        List<WebElement> names = driver.findElements(By.cssSelector(".problem label"));

        for (WebElement labelElement : names) {
            //System.out.println(labelElement.isDisplayed());
            //System.out.println(labelElement.isEnabled());
            System.out.println(String.format("Title " + labelElement.getAttribute("textContent") + " has id " + labelElement.getAttribute("for")));
        }
        return null;
    }

    public void clickZoomOut() {
    WebElement zoomOut = driver.findElement(By.xpath("//a[@title='Zoom out']"));

        do {
            zoomOut.click();
        } while (!zoomOut.getAttribute("class").contains("disabled"));
}
    public void clickFilter() {

        driver.findElement(By.xpath("//div[@class='b-left-side__pointer']")).click();

        List<WebElement> filters = driver.findElements(By.cssSelector(".problem label[for^='type']"));

        for (int i = 0; i < filters.size(); i++) {
            filters.get(i).click();
            }

        for (int i = 0; i < filters.size(); i++) {
            filters.get(i).click();
            anyPage.clickAtProblemByCoordinate(latitude, longitude);
        }
    }

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

//        List<WebElement> calendarGrid = datePickers.findElements(By.xpath("//div/ul/li/div[@class='ng-isolate-scope']"));
//
//        for (WebElement datePickers : calendarGrid) {
//
//            datePickers.findElement(By.className("ng-binding")).click();
//            datePickers.findElement(By.className("ng-binding")).click();
//
//            datePickers.findElement(By.xpath("//td/button/span[(text()='14')]")).click();
//            datePickers.findElement(By.xpath("//td/button/span[(text()='трав.')]")).click();
//            datePickers.findElement(By.xpath("//td/button/span[(text()='10')]")).click();
//        }

//    public void datePickerSetDate(){
//    WebElement calendarGrid = driver.findElement(By.cssSelector(".datepicker"));
//    calendarGrid.click();
//    List<WebElement> calendarGridPickDate = driver.findElements(By.xpath("//div/ul/li/div[@class='ng-isolate-scope']"));
//
//        for (WebElement datePickerSetDate : calendarGridPickDate){
//
//        datePickerSetDate.findElement(By.className("ng-binding")).click();
//        datePickerSetDate.findElement(By.className("ng-binding")).click();
//
//        datePickerSetDate.findElement(By.xpath("(//td/button/span)[14]")).click();
//        datePickerSetDate.findElement(By.xpath("//td/button/span[(text()='трав.')]")).click();
//        datePickerSetDate.findElement(By.xpath("//td/button/span[(text()='10')]")).click();
//        }
//        }
    }
}