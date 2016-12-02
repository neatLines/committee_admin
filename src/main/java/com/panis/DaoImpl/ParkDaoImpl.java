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
public class ParkDaoImpl implements ParkDao{

    private DataBaseConnect connect = null;
    private PreparedStatement statement = null;

    public ParkDaoImpl() {
        super();
        connect= new DataBaseConnect();
    }

    @Override
    public List<ParkTableEntity> findAll() throws Exception{
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM park_table";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<ParkTableEntity> list;
        list = getList(rs);
        connect.close();
        return list;
    }

    @Override
    public List<ParkTableEntity> findOrderByUId(Integer uId) throws Exception{
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM park_table WHERE u_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,uId);
        ResultSet rs = statement.executeQuery();
        List<ParkTableEntity> list;
        list = getList(rs);
        connect.close();
        return list;
    }

    @Override
    public List<ParkTableEntity> findOrderByParkId(Integer parkId) throws Exception {
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM park_table WHERE park_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,parkId);
        ResultSet rs = statement.executeQuery();
        List<ParkTableEntity> list;
        list = getList(rs);
        connect.close();
        return list;
    }
    List<ParkTableEntity> getList(ResultSet resultSet) throws SQLException {
        Connection connection = connect.getConnection();
        List<ParkTableEntity> list = new ArrayList<ParkTableEntity>();
        while(resultSet.next()){
            ParkTableEntity parkTableEntity = new ParkTableEntity();
            int park_id = resultSet.getInt(1);
            String place = resultSet.getString(2);
            int u_id = resultSet.getInt(3);
            parkTableEntity.setParkId(park_id);
            parkTableEntity.setPlace(place);
            parkTableEntity.setuId(u_id);
            list.add(parkTableEntity);
        }
        connect.close();
        return list;
    }
}