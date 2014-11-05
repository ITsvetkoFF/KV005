package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.DropZoneUploadThread;
import utility.FileChooserThread;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class AnyPage extends MapPage implements IAnyPage {

    public static final By ADD_PROBLEM_BUTTON = By.xpath("//*[@class='navbar-brand b-menu__button']");
    public static final By ADD_PROBLEM_NEXT_TAB2_BUTTON = By.xpath("//button[@class='btn btn-default btn-sm ng-scope']");
    public static final By PROBLEM_NAME_TEXT_BOX = By.id("problemName");
    public static final By PROBLEM_TYPE_DROP_DOWN_LIST = By.cssSelector("#select-field option");
    public static final By PROBLEM_DESCRIPTION_FIELD = By.id("description-field");
    public static final By PROBLEM_PROPOSE_FIELD = By.id("proposal-field");
    public static final By DROP_ZONE = By.xpath("//div[contains(@class,'dz-clickable')]/span");
    public static final By IMAGE_COMMENT_TEXT_BOX = By.cssSelector("textarea.comment_field");
    public static final By ADD_PROBLEM_SUBMIT_BUTTON = By.id("btn-submit");
    public static final By ALERT = By.className("alert");
    public static final By CLOSE_CROSS = By.className("close");
    public static final By LOGIN_LINK = By.linkText("\u0412\u0425\u0406\u0414");
    public static final By BODY = By.xpath("//body");
    public static final By ADD_PROBLEM_TAB3_IMAGE = By.className("fa-file-photo-o");
    public static final By OPEN_FIRST_RESOURCE_MENU = By.className("fa-caret-down");
    public static final By FIRST_RESOURCE_IN_MENU = By.xpath("//ul[@id='b-header__resources']/li/a");
    public static final By TITLE_EXPRESSION = By.tagName("h1");
    public static final By EMAIL_FIELD = By.name("email");
    public static final By PASSWORD_FIELD = By.name("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By USER_PICTOGRAM = By.className("fa-user");
    public static final By LOGOUT_LINK = By.linkText("\u0412\u0418\u0419\u0422\u0418");
    public static final By REGISTRATION_LINK = By.xpath("//button[@id='register-button']");
    public static final By FIRST_NAME_FIELD = By.name("first_name");
    public static final By LAST_NAME_FIELD = By.name("last_name");
    public static final By PASSWORD_REPEAT_FIELD = By.name("password_second");
    public static final By REGISTRATION_SUBMIT_BUTTON = By.className("b-form__button");
    public static final By LOGGED_IN__USER_NAME = By.xpath("//i[contains(@class, 'fa-user')]/..");

    private WebDriver driver;

    public AnyPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public String getFirstResourceTitleFromMenu() {

        driver.findElement(OPEN_FIRST_RESOURCE_MENU).click();
        String linkText = driver.findElement(FIRST_RESOURCE_IN_MENU).getText();

        return linkText;
    }

    @Override
    public String getFirstResourceTitleFromOpenedResource() {

        driver.findElement(FIRST_RESOURCE_IN_MENU).click();
        String linkText = driver.findElement(TITLE_EXPRESSION).getText();

        return linkText;
    }


    @Override
    public void logIn(String email, String password) {
        driver.findElement(LOGIN_LINK).click();
        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        driver.findElement(PASSWORD_FIELD).clear();
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Override
    public void logOut() {
        driver.findElement(USER_PICTOGRAM).click();
        driver.findElement(LOGOUT_LINK).click();
    }

    @Override
    public void register(String first_name, String last_name, String email, String password) {
        driver.findElement(LOGIN_LINK).click();
        driver.findElement(REGISTRATION_LINK).click();
        driver.findElement(FIRST_NAME_FIELD).clear();
        driver.findElement(FIRST_NAME_FIELD).sendKeys(first_name);
        driver.findElement(LAST_NAME_FIELD).clear();
        driver.findElement(LAST_NAME_FIELD).sendKeys(last_name);
        driver.findElements(EMAIL_FIELD).get(1).sendKeys(email);
        driver.findElements(PASSWORD_FIELD).get(1).sendKeys(password);
        driver.findElement(PASSWORD_REPEAT_FIELD).clear();
        driver.findElement(PASSWORD_REPEAT_FIELD).sendKeys(password);
        driver.findElement(REGISTRATION_SUBMIT_BUTTON).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        driver.findElement(CLOSE_CROSS).click();
    }

    @Override
    public String getLoggedInUserName() {
        String result;
        result = driver.findElement(LOGGED_IN__USER_NAME).getText();
        return result;
    }

    @Override
    public void addProblemToVisibleCenter(double latitude, double longitude,
                                          String problemName, String problemType,
                                          String problemDescription, String problemPropose,
                                          List<String> imageUrls, List<String> imageComments) {

        driver.manage().window().maximize();
        driver.findElement(ADD_PROBLEM_BUTTON).click();
        setVisibleView(latitude, longitude, 18);
        clickAtVisibleMapCenter(0);
        driver.findElement(ADD_PROBLEM_NEXT_TAB2_BUTTON).click();
        driver.findElement(PROBLEM_NAME_TEXT_BOX).sendKeys(problemName);

        List<WebElement> elements = driver.findElements(PROBLEM_TYPE_DROP_DOWN_LIST);
        for (WebElement element: elements) {
            if (problemType.equals(element.getText()))
                element.click();
        }

        driver.findElement(PROBLEM_DESCRIPTION_FIELD).sendKeys(problemDescription);
        driver.findElement(PROBLEM_PROPOSE_FIELD).sendKeys(problemPropose);
        driver.findElement(BODY).sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME));
        driver.findElement(ADD_PROBLEM_TAB3_IMAGE).click();

        for (String url: imageUrls) {
            if (url.length() == 0) {
                continue;
            }
            Thread thread = new DropZoneUploadThread(url);
            thread.start();
            driver.findElement(DROP_ZONE).click();
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
            }
            thread.interrupt();
        }

        List<WebElement> commentElements = driver.findElements(IMAGE_COMMENT_TEXT_BOX);
        int i = 0;
        for (WebElement element: commentElements) {
            element.sendKeys(imageComments.get(i));
            i++;
        }

        driver.findElement(ADD_PROBLEM_SUBMIT_BUTTON).click();
        if (driver.findElements(LOGIN_LINK).size() > 0) {
            WebElement alert = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(ALERT));
            alert.findElement(CLOSE_CROSS).click();
        }
    }
}