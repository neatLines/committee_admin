package com.panis.DaoImpl;

import com.panis.Dao.UserDao;
import com.panis.model.UserTableEntity;

import java.sql.Connection;
import java.sql.ResultSet;
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
        String sql = "SELECT * FROM user_table WHERE user_name = ?";
        List<UserTableEntity> list;
        statement = connection.prepareStatement(sql);
        statement.setString(1,userName);
        ResultSet rs = statement.executeQuery();
        list = getList(rs,UserTableEntity.class);
        flush(connect);
        return list;
    }

    @Override
    public List findAllLinkPersonnel() throws Exception {
        Connection connection = connect.getConnection();
        String sql = "SELECT user_table.u_id, u_name, phone_number, user_name, duty FROM personnel_table RIGHT JOIN user_table ON personnel_table.u_id = user_table.u_id";
        List list;
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        list = resultSetToList(rs);
        flush(connect);

        return list;
    }


}
