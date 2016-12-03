package com.panis.util;

import java.util.HashMap;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public class Config {

    public String server;
    public String user;
    public String pass;
    public String port;
    public String dbname;

    public HashMap<String,String> className;

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbname() {
        return dbname;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setClassName(HashMap<String, String> className) {
        this.className = className;
    }

    public HashMap<String, String> getClassName() {
        return className;
    }

    public String getConnString() {

        String connString = "jdbc:mysql://"+server+"/"
                + dbname + "?user=" + user + "&password=" + pass
                + "&useUnicode=true&characterEncoding=UTF-8";
        return connString;

    }

}