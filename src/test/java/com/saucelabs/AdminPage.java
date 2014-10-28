package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yermek on 20.10.2014.
 */
public class AdminPage extends AnyPage implements IAdminPage {
    private WebDriver driver;

    public AdminPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public int getProblemForModerationCount() {
        int result = 0;
        List<WebElement> problems = driver.findElements(By.className("b-new-items__item"));
        result = problems.size();
        return result;
    }

    public List<String> getProblemForModerationNames() {
        List<String> results = new ArrayList<String>(getProblemForModerationCount());
        List<WebElement> problems = driver.findElements(By.className("b-new-items__item"));
        for (WebElement problem: problems) {
            results.add(problem.getText());
        }
        return results;
    }

    public List<WebElement> getProblems() {
        List<WebElement> results = driver.findElements(By.className("b-new-items__item"));
        return results;
    }

    public String getProblemTitle() {
        return driver.findElement(By.xpath("//editproblemtitle/span")).getText();
    }

    public void clickProblemCheck(WebElement problem) {
        problem.findElement(By.xpath("..//ul/li")).click();
        return;
    }

    public void approveProblem(String problemName) {
        List<WebElement> problems = driver.findElements(By.className("b-new-items__item"));
        for (WebElement problem: problems) {
            if (problemName.equals(problem.getText())) {
                clickProblemCheck(problem);
            }
        }
    }

    public boolean checkProblemIsUnderModeration(String problemName) {
        List<WebElement> problems = driver.findElements(By.className("b-new-items__item"));
        for (WebElement problem: problems) {
            if (problemName.equals(problem.getText())) {
                return true;
            }
        }
        return false;
    }
}
