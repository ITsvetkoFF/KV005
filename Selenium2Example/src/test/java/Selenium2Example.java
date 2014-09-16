import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by vmoroztc on 16.09.2014.
 */
public class Selenium2Example {
    public static void main(String[] args){
        // Download it => http://chromedriver.storage.googleapis.com/index.html?path=2.10/
        //Unzip it. Copy and past path next line
        System.setProperty("webdriver.chrome.driver", ".\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("Page title is: " + driver.getTitle());
        (new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        System.out.println("Page title is:" + driver.getTitle());
        driver.quit();

    }

}
