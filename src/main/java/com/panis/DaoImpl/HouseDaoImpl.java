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

}
