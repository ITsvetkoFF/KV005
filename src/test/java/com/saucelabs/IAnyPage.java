package com.saucelabs;

public interface IAnyPage {
    String getFirstResourceTitleFromMenu();
    String getFirstResourceTitleFromOpenedResource();
    void logIn(String email, String password);
    void logOut();
}
