package com.panis.DaoImpl;

import com.panis.Dao.HouseDao;
import com.panis.DataBaseConnect.DataBaseConnect;
import com.panis.model.HouseTableEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuyipeng on 02/12/2016.
 */
public class HouseDaoImpl extends BaseDaoImpl implements HouseDao{

    public HouseDaoImpl() {
        super();
    }

    // TODO: 02/12/2016 每个方法后都close statement
    // TODO: 02/12/2016 不必手动关闭connect
    // TODO: 02/12/2016 思考合适的关闭时间


    @Override
    public boolean updateHouseInfo(Integer uId, String apHouse, String pannant, String state, int houseId) throws Exception{
        Connection connection = connect.getConnection();
        String sql = "UPDATE house_table SET u_id = ?, ap_house = ?, pannant = ?, state = ? WHERE house_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,uId);
        statement.setString(2,apHouse);
        statement.setString(3,pannant);
        statement.setString(4,state);
        statement.setInt(5,houseId);
        int update = statement.executeUpdate();
        connect.close();
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }


    List<HouseTableEntity> getList(ResultSet resultSet) throws SQLException {
        List<HouseTableEntity> list = new ArrayList<HouseTableEntity>();
        while(resultSet.next()){
            HouseTableEntity houseTableEntity = new HouseTableEntity();
            int houseId = resultSet.getInt(1);
            int u_id = resultSet.getInt(2);
            String pannant = resultSet.getString(3);
            String ap_house = resultSet.getString(4);
            String state = resultSet.getString(5);
            houseTableEntity.setHouseId(houseId);
            houseTableEntity.setuId(u_id);
            houseTableEntity.setApHouse(ap_house);
            houseTableEntity.setPannant(pannant);
            houseTableEntity.setState(state);
            list.add(houseTableEntity);
        }
        return list;
    }
}
