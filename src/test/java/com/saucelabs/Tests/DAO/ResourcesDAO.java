package com.saucelabs.Tests.DAO;


import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
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
        Statement statement = getConnection("jdbc:mysql://localhost:3306/enviromap?useUnicode=true&characterEncoding=UTF-8","root", "").createStatement();
        ResultSet resultSet = statement.executeQuery("select * from resources;");
        List<String> result = new ArrayList<>();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("Title"));
            result.add(resultSet.getString("Title"));
        }
        statement.close();
        return result;
    }

    public void addResourceToDB(String alias, String title, String content, int isResource) throws SQLException, ClassNotFoundException {

        String query = "insert into resources (Alias, Title, Content, IsResource)"
                + " values (?, ?, ?, ?)";
        PreparedStatement preparedStmt = (PreparedStatement) getConnection("jdbc:mysql://localhost:3306/enviromap?useUnicode=true&characterEncoding=UTF-8","root", "").prepareStatement(query);

        preparedStmt.setString  (1, alias);
        preparedStmt.setString  (2, title);
        preparedStmt.setString  (3, content);
        preparedStmt.setInt     (4, isResource);

        preparedStmt.executeUpdate();

        preparedStmt.close();
        }

    public void editResourceInDB(String newAlias, String newTitle, String newContent, String existedAlias) throws SQLException, ClassNotFoundException {

        String query = "update resources set Alias = ?, Title = ?, Content = ?)"
                + " where (Alias = ?)";
        PreparedStatement preparedStmt = (PreparedStatement) getConnection("jdbc:mysql://localhost:3306/enviromap?useUnicode=true&characterEncoding=UTF-8","root", "").prepareStatement(query);

        preparedStmt.setString  (1, newAlias);
        preparedStmt.setString  (2, newTitle);
        preparedStmt.setString  (3, newContent);
        preparedStmt.setString  (4, existedAlias);

        preparedStmt.executeUpdate();

        preparedStmt.close();
    }

    public void deleteResourceFromDB(String newTitle) throws SQLException, ClassNotFoundException {

        String query = "delete from resources where Alias = newTitle)";
        PreparedStatement preparedStmt = (PreparedStatement) getConnection("jdbc:mysql://localhost:3306/enviromap?useUnicode=true&characterEncoding=UTF-8","root", "").prepareStatement(query);

        preparedStmt.executeUpdate();

        preparedStmt.close();
    }
}
