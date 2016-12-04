package com.panis.DaoImpl;

import com.panis.DataBaseConnect.DataBaseConnect;
import com.panis.model.ParkTableEntity;
import com.panis.model.RuleBreakTableEntity;
import com.panis.model.UserTableEntity;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.panis.util.Tool.humpToLine2;
import static org.junit.Assert.*;

/**
 * Created by fuyipeng on 03/12/2016.
 */
public class BaseDaoImplTest {
    @Test
    public void findAllLinkUserTable() throws Exception {

    }

    @Test
    public void delete() throws Exception {
        DataBaseConnect connect= new DataBaseConnect();
        PreparedStatement statement;
        UserTableEntity object = new UserTableEntity();
        object.setuId(7);

        Class cl = object.getClass();
        Connection connection = connect.getConnection();
        HashMap<String,String> map = connect.getClassName();
        Field[] fields = cl.getDeclaredFields();
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(map.get(cl.getSimpleName()));
        sql.append(" WHERE ");
        sql.append(humpToLine2(fields[0].getName()));
        sql.append(" = ?");
        statement = connection.prepareStatement(String.valueOf(sql));
        fields[0].setAccessible(true);
        statement.setObject(1,fields[0].get(object));

        int delete = 0;
        delete = statement.executeUpdate();
        connect.close();
    }

    @Test
    public void updateById() throws Exception {

        DataBaseConnect connect= new DataBaseConnect();
        PreparedStatement statement;

        RuleBreakTableEntity object = new RuleBreakTableEntity();
        object.setBreakLogId(3);
        object.setBreakUId(1);
        object.setAdminUId(1);
        object.setDecribe("asdfgasdfsa");
        object.setFlag(true);

        Class cl = object.getClass();
        Connection connection = connect.getConnection();
        HashMap<String, String> map = connect.getClassName();
        Field[] fields = cl.getDeclaredFields();
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(map.get(cl.getSimpleName()));
        sql.append(" SET ");
        for (int i = 1; i < fields.length; i++) {
            sql.append(humpToLine2(fields[i].getName()));
            sql.append(" = ?, ");
        }
        sql.deleteCharAt(sql.length()-2);
        sql.append(" WHERE ");
        sql.append(humpToLine2(fields[0].getName()));
        sql.append(" = ?");
        System.out.println(sql);
        statement = connection.prepareStatement(String.valueOf(sql));
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            System.out.println(fields[i].get(object));
            statement.setObject(i,fields[i].get(object));
        }
        fields[0].setAccessible(true);
        statement.setObject(fields.length,fields[0].get(object));
        int update = 0;
        update = statement.executeUpdate();
    }

    @Test
    public void findAll() throws Exception {

        DataBaseConnect connect= new DataBaseConnect();
        PreparedStatement statement;
        Class cl = UserTableEntity.class;

        Connection connection = connect.getConnection();
        HashMap<String,String> map = connect.getClassName();
//        Iterator iter = map.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            System.out.println((String)entry.getKey()+(String)entry.getValue());
//        }
        String sql = "SELECT * FROM "+map.get(cl.getSimpleName());
//        System.out.println(sql);
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        System.out.println(getList(rs,cl).get(0).toString());
    }


    @Test
    public void findById() throws Exception {

    }

    @Test
    public void insert() throws Exception {

        DataBaseConnect connect= new DataBaseConnect();
        PreparedStatement statement;

        RuleBreakTableEntity object = new RuleBreakTableEntity();
//        object.setBreakLogId(1);
        object.setBreakUId(1);
        object.setAdminUId(1);
        object.setDecribe("asdfgasdfsadf");
        object.setFlag(false);


        Class cl = object.getClass();
        Connection connection = connect.getConnection();
        Field[] fields = cl.getDeclaredFields();
        HashMap<String,String> map = connect.getClassName();
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(map.get(cl.getSimpleName()));
        sql.append(" (");
        for (int i = 1; i < fields.length; i++) {
            sql.append(humpToLine2(fields[i].getName()));
            sql.append(", ");
        }
        sql.deleteCharAt(sql.length()-2);
        sql.append(") VALUES (");

        for (int i = 1; i< fields.length;i++) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        System.out.println(sql);


        statement = connection.prepareStatement(String.valueOf(sql));

        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            System.out.println(fields[i].get(object));
            statement.setObject(i,fields[i].get(object));
        }

        int insert = statement.executeUpdate();

    }



    public List getList(ResultSet rs, Class cl) throws Exception{
        Field[] fields = cl.getDeclaredFields();
        List list = new ArrayList();
        while (rs.next()) {
            Object object = cl.newInstance();
            for (Field ff: fields) {
                ff.setAccessible(true);
                System.out.println(ff.toString());
                ff.set(object,rs.getObject(humpToLine2(ff.getName())));
            }
            list.add(object);
        }

        return list;
    }
}