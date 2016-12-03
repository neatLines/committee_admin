package com.panis.DaoImpl;

import com.panis.Dao.BaseDao;
import com.panis.DataBaseConnect.DataBaseConnect;
import com.panis.model.UserTableEntity;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static com.panis.util.Tool.humpToLine2;

/**
 * Created by fuyipeng on 02/12/2016.
 */
public class BaseDaoImpl implements BaseDao {
    public DataBaseConnect connect = null;
    public PreparedStatement statement = null;

    public BaseDaoImpl() {
        super();
        connect= new DataBaseConnect();

    }

    @Override
    public List findAll(Class cl) throws Exception {
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

        return getList(rs,cl);
    }

    @Override
    public boolean insert(Object object) throws Exception {
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
        System.out.println(sql);
        int insert = statement.executeUpdate();

        if (insert > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List findById(Object object) throws Exception {
        Class cl = object.getClass();
        Connection connection = connect.getConnection();
        HashMap<String,String> map = connect.getClassName();
        Field[] fields = cl.getDeclaredFields();
        StringBuilder sql = new StringBuilder("SELECT * FROM ");
        sql.append(map.get(cl.getSimpleName()));
        sql.append(" WHERE ");
        sql.append(humpToLine2(fields[0].getName()));
        sql.append(" = ");
        sql.append(fields[0].get(object));
        System.out.println(sql);
        statement = connection.prepareStatement(String.valueOf(sql));
        ResultSet rs = statement.executeQuery();

        return getList(rs,cl);
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
