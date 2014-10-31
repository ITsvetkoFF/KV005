package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Constant;
import utility.ExcelUtils;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Olya on 10/26/14.
 */
public class ResourcesPage extends AnyPage implements IResourcesPage{
    private WebDriver driver;

    public ResourcesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void createResource(String sTitle, String sAlias, String sBody, String sPlaceToSave) throws Exception{

        driver.findElement(By.linkText("РЕСУРСИ")).click();
        driver.findElement(By.linkText("ДОДАТИ НОВИЙ РЕСУРС")).click();
        driver.findElement(By.name("Title")).sendKeys(sTitle);
        driver.findElement(By.name("Alias")).sendKeys(sAlias);
        driver.findElement(By.cssSelector("div[id^='taTextElement']")).sendKeys(sBody);
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(driver.findElement(By.name("IsResource")));
        select.selectByVisibleText(sPlaceToSave);
        driver.findElement(By.cssSelector(".b-form__button.editor_button")).click();
        driver.findElement(By.className("b-header__logo")).click();

    }

    public String existResource(String resource) throws  Exception{
        String value = null;
        driver.findElement(By.className("b-header__logo")).click();
        //try {
        //WebDriverWait wait = new WebDriverWait(driver, 3);
        //WebElement elements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b-menu__button.ng-scope")));
        List<WebElement> resources1 = driver.findElements(By.cssSelector(".b-menu__button.ng-scope"));
        for (WebElement listElement : resources1){
            String searchText = listElement.getText();
            if (searchText.equals(resource.toUpperCase())){
                value = "У верхньому меню";
            }
        }
        //}
        //catch (Exception e) {};


        driver.findElement(By.linkText("РЕСУРСИ")).click();
        List<WebElement> resources2 = driver.findElements(By.cssSelector("#b-header__resources li"));
        for (WebElement listElement : resources2){
            //System.out.println(listElement.getText());
            if (listElement.getText().equals(resource)){
                value = "В розділі \"Ресурси\"";
            }
        }
        driver.findElement(By.className("b-header__logo")).click();
        return value;
    }

    public void editResourceHeader(String sTitle, String sTextToAdd) throws Exception{

        List<WebElement> resources = driver.findElements(By.cssSelector(".b-menu__button.ng-scope"));
        for (WebElement listElement : resources){
            String searchText = listElement.getText();
            if (searchText.equals(sTitle.toUpperCase())){
                listElement.findElement(By.cssSelector(".fa.fa-pencil.fa-xs.ng-scope")).click();
                driver.findElement(By.name("Title")).sendKeys(sTextToAdd);
                driver.findElement(By.cssSelector(".b-form__button.editor_button")).click();
                break;
            }
        }
        driver.findElement(By.className("b-header__logo")).click();
    }

    public void editResourceList(String sTitle, String sTextToAdd) throws  Exception{

        driver.findElement(By.linkText("РЕСУРСИ")).click();
        List<WebElement> resources = driver.findElements(By.cssSelector("#b-header__resources li"));
        for (WebElement listElement : resources){
            if (listElement.getText().equals(sTitle)){
                listElement.findElement(By.cssSelector(".fa.fa-pencil.ng-scope")).click();
                driver.findElement(By.name("Title")).sendKeys(sTextToAdd);
                driver.findElement(By.cssSelector(".b-form__button.editor_button")).click();
                break;
            }
        }
        driver.findElement(By.className("b-header__logo")).click();
    }

    public void editResource(String sTitle, String sTextToAdd) throws  Exception{
        String place = existResource(sTitle);
        if (place.equals("В розділі \"Ресурси\"")){
            editResourceList(sTitle, sTextToAdd);
        }
        else if (place.equals("У верхньому меню")){
            editResourceHeader(sTitle, sTextToAdd);
        }
    }

    public void deleteResourceFromHeader(String deleteTitle) throws Exception{

        driver.findElement(By.className("b-header__logo")).click();
        List<WebElement> resources = driver.findElements(By.cssSelector(".b-menu__button.ng-scope"));
        for (WebElement listElement : resources){
            String searchText = listElement.getText();
            if (searchText.equals(deleteTitle.toUpperCase())){
                listElement.findElement(By.cssSelector(".fa.fa-trash.fa-xs.ng-scope")).click();
                //driver.findElement(By.partialLinkText("Видалити ресурс")).click();
                driver.findElement(By.cssSelector(".btn.btn-warning.ng-binding")).click();
                break;
            }
        }
        driver.findElement(By.className("b-header__logo")).click();

    }

    public void deleteResourceFromList(String deleteTitle) throws Exception{

        driver.findElement(By.className("b-header__logo")).click();
        driver.findElement(By.linkText("РЕСУРСИ")).click();
        List<WebElement> resources = driver.findElements(By.cssSelector("#b-header__resources li"));
        for (WebElement listElement : resources){
            if (listElement.getText().equals(deleteTitle)){
                listElement.findElement(By.cssSelector(".fa.fa-trash.ng-scope")).click();
                //driver.findElement(By.partialLinkText("Видалити ресурс")).click();
                driver.findElement(By.cssSelector(".btn.btn-warning.ng-binding")).click();
                break;
            }
        }
    }

    public void deleteResource(String deleteTitle) throws  Exception{
        String place = existResource(deleteTitle);
        if (place.equals("В розділі \"Ресурси\"")){
            deleteResourceFromList(deleteTitle);
        }
        else if (place.equals("У верхньому меню")){
            deleteResourceFromHeader(deleteTitle);
        }
    }

}
