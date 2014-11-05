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
    public static final By LOGIN = By.linkText("\u0412\u0425\u0406\u0414");
    public static final By BODY = By.xpath("//body");
    public static final By ADD_PROBLEM_TAB3_IMAGE = By.className("fa-file-photo-o");

    private WebDriver driver;

    public AnyPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public String getFirstResourceTitleFromMenu() {

        driver.findElement(By.className("fa-caret-down")).click();
        String linkText = driver.findElement(By.xpath("//ul[@id='b-header__resources']/li/a")).getText();

        return linkText;
    }

    @Override
    public String getFirstResourceTitleFromOpenedResource() {

        driver.findElement(By.xpath("//ul[@id='b-header__resources']/li/a")).click();
        String linkText = driver.findElement(By.tagName("h1")).getText();

        return linkText;
    }


    @Override
    public void logIn(String email, String password) {
        driver.findElement(LOGIN).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Override
    public void logOut() {
        driver.findElement(By.className("fa-user")).click();
        driver.findElement(By.linkText("\u0412\u0418\u0419\u0422\u0418")).click();
    }

    @Override
    public void register(String first_name, String last_name, String email, String password) {
        driver.findElement(LOGIN).click();
        driver.findElement(By.xpath("//button[@id='register-button']")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("first_name")).clear();
        driver.findElement(By.name("first_name")).sendKeys(first_name);
        driver.findElement(By.name("last_name")).clear();
        driver.findElement(By.name("last_name")).sendKeys(last_name);
        driver.findElement(By.name("password_second")).clear();
        driver.findElement(By.name("password_second")).sendKeys(password);
        driver.findElement(By.className("b-form__button")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        driver.findElement(CLOSE_CROSS).click();
    }

    @Override
    public String getLoggedInUserName() {
        String result;
        result = driver.findElement(By.xpath("(//a[contains(@class, 'b-menu__button')])[3]")).getText();
        return result;
    }

    @Override
    public void addProblem(double latitude, double longitude,
                           String problemName, String problemType,
                           String problemDescription, String problemPropose,
                           List<String> imageUrls, List<String> imageComments) {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.findElement(ADD_PROBLEM_BUTTON).click();
        clickAtPagesCenter();
        driver.findElement(ADD_PROBLEM_NEXT_TAB2_BUTTON).click();
        driver.findElement(PROBLEM_NAME_TEXT_BOX).sendKeys(problemName);

        List<WebElement> elements = driver.findElements(PROBLEM_TYPE_DROP_DOWN_LIST);
        for (WebElement element : elements) {
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
            Thread thread = new FileChooserThread(url);
            thread.start();
            driver.findElement(DROP_ZONE).click();
            try {
                Thread.sleep(3000);
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
        if (driver.findElements(LOGIN).size() > 0) {
            WebElement alert = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(ALERT));
            alert.findElement(CLOSE_CROSS).click();
        }
    }

    @Override
    public int addProblemOffsetPageCenter(double latitude, double longitude,
                           String problemName, String problemType,
                           String problemDescription, String problemPropose,
                           List<String> imageUrls, List<String> imageComments) {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(ADD_PROBLEM_BUTTON).click();
        int offset = clickOffsetOfMapCenter(latitude, longitude);
        driver.findElement(ADD_PROBLEM_NEXT_TAB2_BUTTON).click();
        driver.findElement(PROBLEM_NAME_TEXT_BOX).sendKeys(problemName);

        List<WebElement> elements = driver.findElements(PROBLEM_TYPE_DROP_DOWN_LIST);
        for (WebElement element : elements) {
            if (problemType.equals(element.getText()))
                element.click();
        }

        driver.findElement(PROBLEM_DESCRIPTION_FIELD).sendKeys(problemDescription);
        driver.findElement(PROBLEM_PROPOSE_FIELD).sendKeys(problemPropose);
        driver.findElement(BODY).sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME));
        driver.findElement(ADD_PROBLEM_TAB3_IMAGE).click();

        for (String url: imageUrls) {
            if (url.length() == 0)
                continue;
            Thread thread = new DropZoneUploadThread(url);
            thread.start();
            driver.findElement(DROP_ZONE).click();
            try {
                Thread.sleep(2500);
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
        if (driver.findElements(LOGIN).size() > 0) {
            WebElement alert = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(ALERT));
            alert.findElement(CLOSE_CROSS).click();
        }
        return offset;
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
        if (driver.findElements(LOGIN).size() > 0) {
            WebElement alert = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(ALERT));
            alert.findElement(CLOSE_CROSS).click();
        }
    }
}