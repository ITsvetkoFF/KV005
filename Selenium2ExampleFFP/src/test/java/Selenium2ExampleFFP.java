import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Yermek on 16.09.2014.
 */
public class Selenium2ExampleFFP {
    public static void main(String[] args) {
        System.setProperty("webdriver.firefox.bin", "..\\FirefoxPortable\\FirefoxPortable.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("Page title is: " + driver.getTitle());
        (new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        System.out.println("Page title is:" + driver.getTitle());
        driver.quit();
    }
}
