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
public class UserDaoImpl implements UserDao {
    private DataBaseConnect connect = null;
    private PreparedStatement statement = null;

    public UserDaoImpl() {
        super();
        connect= new DataBaseConnect();
    }

    @Override
    public List<UserTableEntity> findAll() throws Exception {
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM user_table";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<UserTableEntity> list;
        list = getList(rs);
        return list;
    }

    @Override
    public List<UserTableEntity> findOrderByUserName(String userName) throws Exception{
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM user_table WHERE user_name = ?";
        List<UserTableEntity> list;
        statement = connection.prepareStatement(sql);
        statement.setString(1,userName);
        ResultSet rs = statement.executeQuery();
        list = getList(rs);
        connect.close();
        return list;
    }

    @Override
    public List<UserTableEntity> findOrderByUId(Integer uId) throws Exception {
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM user_table WHERE u_id = ?";
        List<UserTableEntity> list;
        statement = connection.prepareStatement(sql);
        statement.setInt(1,uId);
        ResultSet rs = statement.executeQuery();
        list = getList(rs);
        connect.close();
        return list;
    }

    @Override
    public boolean updateUserInfo(String uName, Integer uAge, String uSex, String phoneNumber, String userName, String password, byte power, Integer uId) throws Exception{
        Connection connection = connect.getConnection();
        String sql = "UPDATE user_table SET u_name = ?,u_age = ?, u_sex = ?, phone_number = ?, user_name = ?, password = ?, power = ? WHERE u_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1,uName);
        statement.setInt(2,uAge);
        statement.setString(3,uSex);
        statement.setString(4,phoneNumber);
        statement.setString(5,userName);
        statement.setString(6,password);
        statement.setByte(7,power);
        statement.setInt(8,uId);

        int update = statement.executeUpdate();
        connect.close();
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void flush() {
    }

    List<UserTableEntity> getList(ResultSet resultSet) throws SQLException {
        List<UserTableEntity> list = new ArrayList<UserTableEntity>();
        while(resultSet.next()){
            UserTableEntity userTableEntity = new UserTableEntity();
            int uId = resultSet.getInt(1);
            String uName = resultSet.getString(2);
            int uAge = resultSet.getInt(3);
            String uSex = resultSet.getString(4);
            String phoneNumber = resultSet.getString(5);
            String password = resultSet.getString(6);
            Byte power = resultSet.getByte(7);
            userTableEntity.setuId(uId);
            userTableEntity.setuName(uName);
            userTableEntity.setuAge(uAge);
            userTableEntity.setuSex(uSex);
            userTableEntity.setPhoneNumber(phoneNumber);
            userTableEntity.setPassword(password);
            userTableEntity.setPower(power);
            list.add(userTableEntity);
        }
        return list;
    }
}
