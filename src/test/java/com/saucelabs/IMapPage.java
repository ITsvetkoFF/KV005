package com.saucelabs;

/**
 * Created by Roma on 23.10.2014.
 */
public interface IMapPage {

    void setPosition();
    void setView(double latitude, double longitude, int zoom);
    void clickAtPagesCenter();
    void clickAtProblemByCoordinate(double latitude, double longitude);

    String getFilterTitle(int typeNumber);

    void clickZoomOut();

    void openFiltersBoard();

    void selectAllExceptOneFilter(int typeNumber) throws Exception;

    void selectOnlyOneFilter(int typeNumber);

    void datePickers();
}
