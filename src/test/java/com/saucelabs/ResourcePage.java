package com.saucelabs;

        import org.apache.xerces.xs.StringList;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.TimeUnit;

/**
 * Created by Olya on 10/22/14.
 */
public class ResourcePage extends AnyPage{
    private WebDriver driver;
    public ResourcePage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password){

        driver.findElement(By.linkText("ВХІД")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

    }

    public void createNewResource(String[] resource, String savePlace) {

        for (int i = 0; i < resource.length; i++){
            driver.findElement(By.linkText("РЕСУРСИ")).click();
            driver.findElement(By.linkText("ДОДАТИ НОВИЙ РЕСУРС")).click();
            driver.findElement(By.name("Title")).sendKeys(resource[i]);
            driver.findElement(By.name("Alias")).sendKeys(resource[i]);
            // JavascriptExecutor js = null;
            // if (driver instanceof JavascriptExecutor) {
            //     js = (JavascriptExecutor) driver;
            // }
            // js.executeScript(" var form = document.getElementsByName(\"EditForm\"); angular.element(form).scope().Content = \"<p>"+resource+"</p>\"");
            // Thread.sleep(3000);
            //driver.findElement(By.xpath("//div[starts-with(@id,'taTextElement')]")).sendKeys(resource);
            driver.findElement(By.cssSelector("div[id^='taTextElement']")).sendKeys(resource[i]);

            org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(driver.findElement(By.name("IsResource")));
            select.selectByVisibleText(savePlace);
            driver.findElement(By.cssSelector(".b-form__button.editor_button")).click();
            //driver.findElement(By.xpath("//div[@class='b-form__button editor_button']"));
            driver.findElement(By.className("b-header__logo")).click();
        }
    }

    public void editResourceFromHeader(String[] text, String addText){

        //List<WebElement> resources = driver.findElements(By.cssSelector(".b-menu__button.ng-scope"));
        for (int i = 0; i<text.length; i++){
            List<WebElement> resources = driver.findElements(By.cssSelector(".b-menu__button.ng-scope"));
            for (WebElement listElement : resources){
                String searchText = listElement.getText();
                if (searchText.equals(text[i].toUpperCase())){
                    listElement.findElement(By.cssSelector(".fa.fa-pencil.fa-xs.ng-scope")).click();
                    driver.findElement(By.name("Title")).sendKeys(addText);
                    driver.findElement(By.cssSelector(".b-form__button.editor_button")).click();
                    break;
                }
            }
            driver.findElement(By.className("b-header__logo")).click();
        }
    }

    public void editResourceFromList(String[] text, String addText){

        for (int i = 0; i<text.length; i++){
            driver.findElement(By.linkText("РЕСУРСИ")).click();
            List<WebElement> resources = driver.findElements(By.cssSelector("#b-header__resources li"));
            for (WebElement listElement : resources){
                //System.out.println(listElement.getText());
                if (listElement.getText().equals(text[i])){
                    listElement.findElement(By.cssSelector(".fa.fa-pencil.ng-scope")).click();
                    driver.findElement(By.name("Title")).sendKeys(addText);
                    driver.findElement(By.cssSelector(".b-form__button.editor_button")).click();
                    break;
                }
            }
            driver.findElement(By.className("b-header__logo")).click();
        }
    }

    public void deleteResourceFromHeader(String[] text){

        driver.findElement(By.className("b-header__logo")).click();
        for (int i = 0; i<text.length; i++){
            List<WebElement> resources = driver.findElements(By.cssSelector(".b-menu__button.ng-scope"));
            for (WebElement listElement : resources){
                String searchText = listElement.getText();
                if (searchText.equals(text[i].toUpperCase())){
                    listElement.findElement(By.cssSelector(".fa.fa-trash.fa-xs.ng-scope")).click();
                }
            }
            driver.findElement(By.className("b-header__logo")).click();
        }
    }

    public void deleteResourceFromList(String[] text){

        driver.findElement(By.className("b-header__logo")).click();
        for (int i = 0; i<text.length; i++){
            driver.findElement(By.linkText("РЕСУРСИ")).click();
            List<WebElement> resources = driver.findElements(By.cssSelector("#b-header__resources li"));
            for (WebElement listElement : resources){
                if (listElement.getText().equals(text[i])){
                    listElement.findElement(By.cssSelector(".fa.fa-trash.ng-scope")).click();
                }
            }
        }
    }

}
