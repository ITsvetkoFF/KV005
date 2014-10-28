package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void CreateResource() throws Exception{

        int i = 1;
        while(!ExcelUtils.getCellData(i,3).isEmpty()) {
            String sTitle = ExcelUtils.getCellData(i, 3);
            String sAlias = ExcelUtils.getCellData(i, 4);
            String sBody = ExcelUtils.getCellData(i, 5);
            String sPlaceToSave = ExcelUtils.getCellData(i, 6);
            driver.findElement(By.linkText("РЕСУРСИ")).click();
            driver.findElement(By.linkText("ДОДАТИ НОВИЙ РЕСУРС")).click();
            driver.findElement(By.name("Title")).sendKeys(sTitle);
            driver.findElement(By.name("Alias")).sendKeys(sAlias);
            driver.findElement(By.cssSelector("div[id^='taTextElement']")).sendKeys(sBody);
            org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(driver.findElement(By.name("IsResource")));
            select.selectByVisibleText(sPlaceToSave);
            driver.findElement(By.cssSelector(".b-form__button.editor_button")).click();
            driver.findElement(By.className("b-header__logo")).click();
            i++;
        }
    }

    public void editResourceHeader() throws Exception{

        //List<WebElement> resources = driver.findElements(By.cssSelector(".b-menu__button.ng-scope"));
        int i = 1;
        while(!ExcelUtils.getCellData(i,3).isEmpty()) {
            String sTitle = ExcelUtils.getCellData(i, 3);
            String sTextToAdd = ExcelUtils.getCellData(i, 7);
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
            i++;
        }
    }

    public void editResourceList() throws  Exception{

        int i = 1;
        while(!ExcelUtils.getCellData(i,3).isEmpty()) {
            String sTitle = ExcelUtils.getCellData(i, 3);
            String sTextToAdd = ExcelUtils.getCellData(i, 7);
            driver.findElement(By.linkText("РЕСУРСИ")).click();
            List<WebElement> resources = driver.findElements(By.cssSelector("#b-header__resources li"));
            for (WebElement listElement : resources){
                //System.out.println(listElement.getText());
                if (listElement.getText().equals(sTitle)){
                    listElement.findElement(By.cssSelector(".fa.fa-pencil.ng-scope")).click();
                    driver.findElement(By.name("Title")).sendKeys(sTextToAdd);
                    driver.findElement(By.cssSelector(".b-form__button.editor_button")).click();
                    break;
                }
            }
            driver.findElement(By.className("b-header__logo")).click();
            i++;
        }
    }

    public void deleteResourceFromHeader() throws Exception{

        int i = 1;
        while(!ExcelUtils.getCellData(i,3).isEmpty()) {
            String sTitle = ExcelUtils.getCellData(i, 3);
            String sTextToAdd = ExcelUtils.getCellData(i, 7);
            String sDeleteTitle = sTitle+sTextToAdd;
            driver.findElement(By.className("b-header__logo")).click();
            List<WebElement> resources = driver.findElements(By.cssSelector(".b-menu__button.ng-scope"));
            for (WebElement listElement : resources){
                String searchText = listElement.getText();
                if (searchText.equals(sDeleteTitle.toUpperCase())){
                    listElement.findElement(By.cssSelector(".fa.fa-trash.fa-xs.ng-scope")).click();
                    break;
                }
            }
            driver.findElement(By.className("b-header__logo")).click();
            i++;
        }
    }

    public void deleteResourceFromList() throws Exception{

        int i = 1;
        while(!ExcelUtils.getCellData(i,3).isEmpty()) {
            String sTitle = ExcelUtils.getCellData(i, 3);
            String sTextToAdd = ExcelUtils.getCellData(i, 7);
            String sDeleteTitle = sTitle+sTextToAdd;
            driver.findElement(By.className("b-header__logo")).click();
            driver.findElement(By.linkText("РЕСУРСИ")).click();
            List<WebElement> resources = driver.findElements(By.cssSelector("#b-header__resources li"));
            for (WebElement listElement : resources){
                if (listElement.getText().equals(sDeleteTitle)){
                    listElement.findElement(By.cssSelector(".fa.fa-trash.ng-scope")).click();
                    break;
                }
            }
            i++;
        }
    }

}
