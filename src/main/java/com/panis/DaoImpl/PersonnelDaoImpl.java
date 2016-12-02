package com.panis.DaoImpl;

import com.panis.Dao.PersonnelDao;
import com.panis.DataBaseConnect.DataBaseConnect;
import com.panis.model.PersonnelTableEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuyipeng on 02/12/2016.
 */
public class PersonnelDaoImpl implements PersonnelDao {
    private DataBaseConnect connect = null;
    private PreparedStatement statement = null;

    public PersonnelDaoImpl() {
        super();
        connect= new DataBaseConnect();
    }

    @Override
    public List<PersonnelTableEntity> findAll() throws Exception{
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM personnel_table";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<PersonnelTableEntity> list;
        list = getList(rs);
        connect.close();
        return list;
    }

    List<PersonnelTableEntity> getList(ResultSet resultSet) throws SQLException {
        Connection connection = connect.getConnection();
        List<PersonnelTableEntity> list = new ArrayList<PersonnelTableEntity>();
        while(resultSet.next()){
            PersonnelTableEntity personnelTableEntity = new PersonnelTableEntity();
            int uId = resultSet.getInt(1);
            String duty = resultSet.getString(2);
            personnelTableEntity.setuId(uId);
            personnelTableEntity.setDuty(duty);
            list.add(personnelTableEntity);
        }
        connect.close();
        return list;
    }
}
