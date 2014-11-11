package com.saucelabs.Tests.DAO;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rotiutc on 05.11.2014.
 */
public class AddProblemDAO extends BaseDAO {

    public Map<String, String> getProblemsById(int problemsId) throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from problems where Id = " + problemsId + ";");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        Map<String, String> problems = new HashMap<>();

        while (resultSet.next()) {
            for (int i = 0; i < columnCount; i++) {
                problems.put(resultSetMetaData.getColumnName(i + 1), resultSet.getString(i + 1));
                System.out.println(resultSetMetaData.getColumnName(i + 1) + " => " + resultSet.getString(i + 1));
            }
        }
        statement.close();
        return problems;
    }

    public List<Map<String, String>> getActivitiesByProblemsId(int problemsId)
                                 throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from activities where Problems_Id = " + problemsId + ";");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        List<Map<String, String>> activities = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                map.put(resultSetMetaData.getColumnName(i + 1), resultSet.getString(i + 1));
                System.out.println(resultSetMetaData.getColumnName(i + 1) + " => " + resultSet.getString(i + 1));
            }
            activities.add(map);
        }
        statement.close();
        return  activities;
    }

    public List<Map<String, String>> getPhotosByProblemsId(int problemsId)
            throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from photos where Problems_Id = " + problemsId + ";");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        List<Map<String, String>> photos = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                map.put(resultSetMetaData.getColumnName(i + 1), resultSet.getString(i + 1));
                System.out.println(resultSetMetaData.getColumnName(i + 1) + " => " + resultSet.getString(i + 1));
            }
            photos.add(map);
        }
        statement.close();
        return  photos;
    }

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        AddProblemDAO addProblemDAO = new AddProblemDAO();
        addProblemDAO.getPhotosByProblemsId(16);
    }
}
