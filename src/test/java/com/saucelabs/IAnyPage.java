package com.saucelabs;

public interface IAnyPage {
    String getFirstResourceTitleFromMenu();
    String getFirstResourceTitleFromOpenedResource();
    void logIn(String email, String password);
    void logOut();
    void register(String first_name, String last_name, String email, String password);
    String getLoggedInUserName();
}
