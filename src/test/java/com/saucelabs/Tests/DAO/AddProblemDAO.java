package com.saucelabs.Tests.DAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rotiutc on 05.11.2014.
 */
public class AddProblemDAO extends BaseDAO {

    public Map<String, String> getMap(int problemID) throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from problems where Id = " + problemID + ";");
        Map<String, String> map = new HashMap<String, String>();

        System.out.println("Id = " + resultSet.getString("Id") + "\t" + "Title = " + resultSet.getString("Title"));
        map.put("Id", resultSet.getString("Id"));
        map.put("Title", resultSet.getString("Title"));

        statement.close();
        return map;
    }
}
