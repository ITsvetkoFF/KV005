package com.saucelabs;

/**
 * Created by Tanya on 23.10.2014.
 */
public interface IMapPage {

    void setPosition();
    void setView(double latitude, double longitude, int zoom);
    void clickAtPagesCenter();
    void clickAtProblemByCoordinate(double latitude, double longitude);
}
