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
public class PersonnelDaoImpl extends BaseDaoImpl implements PersonnelDao {

    public PersonnelDaoImpl() {
        super();
    }


    List<PersonnelTableEntity> getList(ResultSet resultSet) throws SQLException {
        List<PersonnelTableEntity> list = new ArrayList<PersonnelTableEntity>();
        while(resultSet.next()){
            PersonnelTableEntity personnelTableEntity = new PersonnelTableEntity();
            int uId = resultSet.getInt(1);
            String duty = resultSet.getString(2);
            personnelTableEntity.setuId(uId);
            personnelTableEntity.setDuty(duty);
            list.add(personnelTableEntity);
        }
        return list;
    }
}
