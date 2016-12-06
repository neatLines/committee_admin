package com.panis.DaoImpl;

import com.panis.Dao.RuleBreakDao;
import com.panis.model.RuleBreakTableEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public class RuleBreakDaoImpl extends BaseDaoImpl implements RuleBreakDao {


    public RuleBreakDaoImpl() {
        super();
    }


    @Override
    public List findOrderByBreakUId(int breakUId) throws Exception {
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM rule_break_table INNER JOIN user_table ON rule_break_table.admin_u_id = user_table.u_id AND break_u_id = ? FOR UPDATE";
        List list;
        statement = connection.prepareStatement(sql);
        statement.setInt(1,breakUId);
        ResultSet rs = statement.executeQuery();
        list = resultSetToList(rs);
        flush(connect);
        return list;
    }

    @Override
    public List findAllLinkUserAndAdmin() throws Exception {
        Connection connection = connect.getConnection();
        String sql = "SELECT admin_name, u_name, decribe, flag FROM (SELECT u_name AS admin_name, decribe, flag, break_u_id FROM rule_break_table INNER JOIN user_table ON rule_break_table.admin_u_id = user_table.u_id) AS tableA INNER JOIN user_table ON break_u_id = user_table.u_id";
        List list;
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        list = resultSetToList(rs);
        flush(connect);
        return list;
    }

}
