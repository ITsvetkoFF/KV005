package com.saucelabs.Tests.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yermek on 05.11.2014.
 */
public class DeleteProblemDAO extends BaseDAO {
    public List<String> getPhotosLinksFromProblemsIs(int ProblemsId) throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Link FROM Photos WHERE Problems_Id = " +
                ProblemsId + ";");
        List<String> result = new ArrayList<>();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("Link"));
            result.add(resultSet.getString("Link"));
        }
        statement.close();
        return result;
    }
}
