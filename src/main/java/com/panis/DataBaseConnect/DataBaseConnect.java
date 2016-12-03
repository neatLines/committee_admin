package com.panis.DataBaseConnect;

import com.panis.util.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.panis.util.XMLReader;
/**
 * Created by fuyipeng on 01/12/2016.
 */
public class DataBaseConnect {
    Config config;
    private Connection connection = null;

    public DataBaseConnect() {
        super();
        config = XMLReader.getConfig();
    }

    public HashMap<String, String> getClassName() {
        return config.getClassName();
    }

    public Connection getConnection() {
        String url = config.getConnString();
        System.out.println(url);
        boolean flag = false;
        try {
            if (connection.isClosed()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException ne) {
            flag = true;
        }
        try {
            if (flag) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection(url);
                System.out.println("success");
            }
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
