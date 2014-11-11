package com.saucelabs.Tests.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nklimotc on 05.11.2014.
 */
public class ResourcesDAO extends BaseDAO {
    public List<String> getAllAliases() throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from resources;");
        List<String> result = new ArrayList<>();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("Title"));
            result.add(resultSet.getString("Title"));
        }
        statement.close();
        return result;
    }

    public void addAllAliases() throws SQLException, ClassNotFoundException {
        Statement statement1 = getConnection().createStatement();
        ResultSet resultSet = statement1.executeQuery("insert into resources values;");

        statement1.close();
        return ;
    }
}
