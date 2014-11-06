package com.saucelabs.Tests.DAO;

import org.testng.annotations.Test;
import java.sql.*;

/**
 * Created by rotiutc on 05.11.2014.
 */
public class AddProblemDAO extends BaseDAO {

    @Test
    public void connect() throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from problems;");
        while (resultSet.next())
            System.out.println(resultSet.getString("Title"));
        statement.close();
    }
}
