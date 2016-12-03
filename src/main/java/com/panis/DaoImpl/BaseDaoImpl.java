package com.panis.DaoImpl;

import com.panis.Dao.BaseDao;
import com.panis.DataBaseConnect.DataBaseConnect;

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
        List list = new ArrayList();
        Field[] fields = cl.getDeclaredFields();
        Connection connection = connect.getConnection();
        HashMap<String,String> map = connect.getClassName();
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println((String)entry.getKey()+(String)entry.getValue());
        }
        String sql = "SELECT * FROM "+map.get(cl.getSimpleName());
        System.out.println(sql);
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
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
