package com.panis.DaoImpl;

import com.panis.Dao.ParkDao;
import com.panis.DataBaseConnect.DataBaseConnect;
import com.panis.model.ParkTableEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuyipeng on 02/12/2016.
 */
public class ParkDaoImpl extends BaseDaoImpl implements ParkDao{

    public ParkDaoImpl() {
        super();
    }


    @Override
    public List findOrderByUId(Integer uId) throws Exception{
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM park_table WHERE u_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,uId);
        ResultSet rs = statement.executeQuery();
        List list;
        list = resultSetToList(rs);
        flush(connect);
        return list;
    }

    @Override
    public List findOrderByLikePlace(String place) throws Exception {
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM park_table WHERE place LIKE '%"+place+"%'";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<ParkTableEntity> list;
        list = resultSetToList(rs);
        flush(connect);
        return list;
    }

}
