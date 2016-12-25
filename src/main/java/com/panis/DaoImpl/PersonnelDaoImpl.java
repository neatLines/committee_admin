package com.panis.DaoImpl;

import com.panis.Dao.PersonnelDao;

import java.sql.Connection;

/**
 * Created by fuyipeng on 02/12/2016.
 */
public class PersonnelDaoImpl extends BaseDaoImpl implements PersonnelDao {

    public PersonnelDaoImpl() {
        super();
    }



    @Override
    public void changeDuty(int u_id, String duty) throws Exception {
        Connection connection = connect.getConnection();
        String sql1;
        if ("null".equalsIgnoreCase(duty)) {
            sql1 = "DELETE FROM personnel_table WHERE u_id = ?";
            statement = connection.prepareStatement(sql1);
            statement.setInt(1,u_id);
        } else {
            sql1 = "UPDATE personnel_table SET duty = ? WHERE u_id=?";
            statement = connection.prepareStatement(sql1);
            statement.setString(1,duty);
            statement.setInt(2,u_id);
        }
        statement.executeUpdate();
    }

    public void insert(int u_id, String duty) throws Exception {
        Connection connection = connect.getConnection();
        String sql = "INSERT personnel_table (u_id, duty) VALUES (?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,u_id);
        statement.setString(2,duty);
        statement.executeUpdate();
    }

    @Override
    public void changeUserPower(int u_id, int power) throws Exception {
        Connection connection = connect.getConnection();
        String sql = "UPDATE user_table SET power = ? WHERE u_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,power);
        statement.setInt(2,u_id);
        statement.executeUpdate();
    }

}
