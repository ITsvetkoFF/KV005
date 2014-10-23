package com.saucelabs;

public interface IAnyPageRoma {

    String getFirstResourceTitleFromMenu();
    String getFirstResourceTitleFromOpenedResource();
    void logIn(String email, String password);
    void logOut();
    void clickAddProblem ();
    void clickAtPagesCenter();
    void setView(double latitude, double longitude, int zoom);
}
