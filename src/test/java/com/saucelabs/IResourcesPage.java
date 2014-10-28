package com.saucelabs;

import org.openqa.selenium.WebDriver;

/**
 * Created by Olya on 10/28/14.
 */
public interface IResourcesPage {
    void CreateResource() throws Exception;
    void editResourceHeader() throws Exception;
    void editResourceList() throws Exception;
    void deleteResourceFromHeader() throws Exception;
    void deleteResourceFromList() throws Exception;
}
