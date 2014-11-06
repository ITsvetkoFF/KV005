package com.saucelabs;

import java.sql.*;

/**
 * Created by Olya on 11/6/14.
 */
public class DAO{
    public Statement connectDB(String DB_login, String DB_password) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");//эта строка загружает драйвер DB

            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/enviromap", DB_login, DB_password);

            if (connection == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
            }
            Statement statement = connection.createStatement();
            return statement;
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

