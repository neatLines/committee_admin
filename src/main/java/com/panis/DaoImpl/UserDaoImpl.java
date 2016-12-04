package com.panis.DaoImpl;

import com.panis.Dao.UserDao;
import com.panis.DataBaseConnect.DataBaseConnect;
import com.panis.model.UserTableEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    public UserDaoImpl() {
        super();
    }

    @Override
    public List findAllLinkUserTable(Class cl) throws Exception {
        return findAll(cl);
    }

    @Override
    public List<UserTableEntity> findOrderByUserName(String userName) throws Exception{
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM user_table WHERE user_name = ? FOR UPDATE";
        List<UserTableEntity> list;
        statement = connection.prepareStatement(sql);
        statement.setString(1,userName);
        ResultSet rs = statement.executeQuery();
        list = resultSetToList(rs);
        connect.close();
        return list;
    }


}
