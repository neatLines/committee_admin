package com.panis.util;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public class Config {

    public String server;
    public String user;
    public String pass;
    public String port;
    public String dbname;

    public String getConnString() {

        String connString = "jdbc:mysql://"+server+"/"
                + dbname + "?user=" + user + "&password=" + pass
                + "&useUnicode=true&characterEncoding=UTF-8";
        return connString;

    }

}