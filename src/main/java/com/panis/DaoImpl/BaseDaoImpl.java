package com.panis.DaoImpl;

import com.panis.Dao.BaseDao;
import com.panis.DataBaseConnect.DataBaseConnect;
import com.panis.model.UserTableEntity;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.Lock;

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

        StringBuilder sql = new StringBuilder("SELECT * FROM ");
        sql.append(map.get(cl.getSimpleName()));
        System.out.println(sql);
        statement = connection.prepareStatement(String.valueOf(sql));
        ResultSet rs = statement.executeQuery();
//        connect.close();
        List list;
        list = resultSetToList(rs);
        flush(connect);
        return list;
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
            statement.setObject(i,fields[i].get(object));
        }
        System.out.println(sql);
        statement.executeUpdate();
        return flush(connect);
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
        fields[0].setAccessible(true);
        sql.append(fields[0].get(object));

        System.out.println(sql);
        statement = connection.prepareStatement(String.valueOf(sql));
        ResultSet rs = statement.executeQuery();
//        connect.close();
        List list = getList(rs,cl);
        flush(connect);
        return list;
    }

    @Override
    public boolean updateById(List<Object> objects) throws Exception {
        Class cl = objects.get(0).getClass();
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
        for (Object object:objects) {
            statement = connection.prepareStatement(String.valueOf(sql));
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(i, fields[i].get(object));
            }
            fields[0].setAccessible(true);
            statement.setObject(fields.length, fields[0].get(object));
            statement.executeUpdate();
        }
        return flush(connect);

    }

    @Override
    public boolean delete(List<Object> objects) throws Exception {
        Class cl = objects.get(0).getClass();
        Connection connection = connect.getConnection();
        HashMap<String,String> map = connect.getClassName();
        Field[] fields = cl.getDeclaredFields();
        fields[0].setAccessible(true);
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(map.get(cl.getSimpleName()));
        sql.append(" WHERE ");
        sql.append(humpToLine2(fields[0].getName()));
        sql.append(" = ?");
        for (Object object:objects) {
            statement = connection.prepareStatement(String.valueOf(sql));
            statement.setObject(1, fields[0].get(object));
            statement.executeUpdate();
        }
        return flush(connect);
    }

    @Override
    public List findAllLinkUserTable(Class cl) throws Exception {
        Connection connection = connect.getConnection();
        HashMap<String,String> map = connect.getClassName();
        Field[] fields = cl.getDeclaredFields();
        StringBuilder sql = new StringBuilder("SELECT ");
        for (Field ff:fields) {
            sql.append(map.get(cl.getSimpleName()));
            sql.append(".");
            sql.append(humpToLine2(ff.getName()));
            sql.append(", ");
        }
        sql.append("u_name, phone_number FROM ");
        sql.append(map.get(cl.getSimpleName()));
        sql.append(" INNER JOIN user_table WHERE ");
        sql.append(map.get(cl.getSimpleName()));
        sql.append(".");
        sql.append(map.get(map.get(cl.getSimpleName())));
        sql.append(" = user_table.u_id");
        System.out.println(sql);
        statement = connection.prepareStatement(String.valueOf(sql));
        ResultSet rs = statement.executeQuery();
        List list = resultSetToList(rs);
        flush(connect);
        return list;

    }

    public boolean flush(DataBaseConnect connectTemp) {
        try {
            connectTemp.commit();
        } catch (SQLException sqlE) {
            try {
                connectTemp.rollback();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                return false;
            }
            return false;
        } finally {
            connectTemp.close();
            return true;
        }
    }

    public List getList(ResultSet rs, Class cl) throws Exception{

        Field[] fields = cl.getDeclaredFields();
        List list = new ArrayList();
        while (rs.next()) {
            Object object = cl.newInstance();
            for (Field ff: fields) {
                ff.setAccessible(true);
                ff.set(object,rs.getObject(humpToLine2(ff.getName())));
            }
            list.add(object);
        }

        return list;
    }

    public List resultSetToList(ResultSet rs) throws java.sql.SQLException {
        if (rs == null)
            return Collections.EMPTY_LIST;
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数
        List list = new ArrayList();
        Map rowData = new HashMap();
        while (rs.next()) {
            rowData = new HashMap(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
            System.out.println("list:" + list.toString());
        }
        System.out.println("list:" + list.toString());
        return list;
    }
}
