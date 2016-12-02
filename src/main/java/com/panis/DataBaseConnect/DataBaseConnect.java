package com.panis.DataBaseConnect;

import com.panis.util.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.panis.util.XMLReader;
/**
 * Created by fuyipeng on 01/12/2016.
 */
public class DataBaseConnect {
    Config config;
    private Connection connection;

    public Connection getConnection() {
        config = XMLReader.getConfig();
        String url = config.getConnString();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url);
            System.out.println("success");
        } catch (Exception e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
