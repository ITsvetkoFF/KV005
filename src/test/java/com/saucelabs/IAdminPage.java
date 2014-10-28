package com.saucelabs;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Yermek on 20.10.2014.
 */
public interface IAdminPage {
    int getProblemForModerationCount();
    List<String> getProblemForModerationNames();
    List<WebElement> getProblems();
    String getProblemTitle();
    void clickProblemCheck(WebElement problem);
    void approveProblem(String problemName);
    boolean checkProblemIsUnderModeration(String problemName);
}
