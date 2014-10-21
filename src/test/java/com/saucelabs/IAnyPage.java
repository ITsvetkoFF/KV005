package com.saucelabs;

public interface IAnyPage {
    String getFirstResourceTitleFromMenu();
    String getFirstResourceTitleFromOpenedResource();
    void login(String email, String password);
    void logout();
}
