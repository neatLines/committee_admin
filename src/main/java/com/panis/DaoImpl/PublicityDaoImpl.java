package com.panis.DaoImpl;

import com.panis.Dao.PublicityDao;
import com.panis.DataBaseConnect.DataBaseConnect;
import com.panis.model.PublicityTableEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuyipeng on 02/12/2016.
 */
public class PublicityDaoImpl extends BaseDaoImpl implements PublicityDao {

    public PublicityDaoImpl() {
        super();
    }

    @Override
    public List<PublicityTableEntity> findOrderByPId(Integer pId) throws Exception {
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM publicity_table WHERE p_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,pId);
        ResultSet rs = statement.executeQuery();
        List<PublicityTableEntity> list;
        list = getList(rs);
        connect.close();
        return list;
    }

    List<PublicityTableEntity> getList(ResultSet resultSet) throws SQLException {
        List<PublicityTableEntity> list = new ArrayList<PublicityTableEntity>();
        while(resultSet.next()){
            PublicityTableEntity publicityTableEntity = new PublicityTableEntity();
            int pId = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String detial = resultSet.getString(3);
            int writer_id = resultSet.getInt(4);
            Date date = resultSet.getDate(5);
            publicityTableEntity.setpId(pId);
            publicityTableEntity.setTitle(title);
            publicityTableEntity.setDetail(detial);
            publicityTableEntity.setWriterId(writer_id);
            publicityTableEntity.setDate(date);
            list.add(publicityTableEntity);
        }
        return list;
    }
}
