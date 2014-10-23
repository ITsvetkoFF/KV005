package com.saucelabs;

public interface IAnyPageRoma {

    String getFirstResourceTitleFromMenu();
    String getFirstResourceTitleFromOpenedResource();
    void logIn(String email, String password);
    void logOut();
    void clickAddProblem ();
}
