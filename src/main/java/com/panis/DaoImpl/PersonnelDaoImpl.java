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

}
