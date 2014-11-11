package com.saucelabs.Tests.DAO;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olya on 11/8/14.
 */
public class UserInfoDAO extends BaseDAO{

    public String[] getInfo(String DB_login, String DB_password, String DB_URL, String Email) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");//эта строка загружает драйвер DB

        Connection connection = DriverManager.getConnection(DB_URL, DB_login, DB_password);
        if (connection == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users where Email = \"" + Email + "\"");
        String[] result = new String[4];
        while (resultSet.next()) {
            //System.out.println(resultSet.getRow() + ". " + resultSet.getString("Name")
            //+ "\t" + resultSet.getString("Surname") + "\t" + resultSet.getString("UserRoles_Id"));
            result[0] = resultSet.getString("Name");
            result[1] = resultSet.getString("Surname");
            result[2] = resultSet.getString("Password");
            result[3] = resultSet.getString("UserRoles_Id");
        }
        statement.close();
        return result;

    }

    public static String hmacSha1(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());

            // Convert raw bytes to Hex
            byte[] hexBytes = new Hex().encode(rawHmac);

            //  Covert array of Hex bytes to a String
            return new String(hexBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
