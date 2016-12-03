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
    public List<RuleBreakTableEntity> findOrderByBreakUId(int breakUId) throws Exception {
        Connection connection = connect.getConnection();
        String sql = "SELECT * FROM rule_break_table WHERE break_u_id = ?";
        List<RuleBreakTableEntity> list;
        statement = connection.prepareStatement(sql);
        statement.setInt(1,breakUId);
        ResultSet rs = statement.executeQuery();
        list = getList(rs);
        connect.close();
        return list;
    }

    @Override
    public boolean updateRuleBreakTable(Integer adminUId, Integer breakUId, String decribe, Boolean flag, Integer breakLogId) throws Exception{
        Connection connection = connect.getConnection();
        String sql = "UPDATE rule_break_table SET admin_u_id = ?, break_u_id = ?, decribe = ?, flag = ? WHERE break_log_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1,adminUId);
        statement.setInt(2,breakUId);
        statement.setString(3,decribe);
        statement.setBoolean(4,flag);
        statement.setInt(5,breakLogId);
        int update = statement.executeUpdate();
        connect.close();
        if (update > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void flush() {
    }

    List<RuleBreakTableEntity> getList(ResultSet resultSet) throws SQLException {
        List<RuleBreakTableEntity> list = new ArrayList<RuleBreakTableEntity>();
        while(resultSet.next()){
            RuleBreakTableEntity ruleBreakTableEntity = new RuleBreakTableEntity();
            int breakLogId = resultSet.getInt(1);
            int adminUid = resultSet.getInt(2);
            int breakUid = resultSet.getInt(3);
            String decribe = resultSet.getString(4);
            Boolean flag = resultSet.getBoolean(5);
            ruleBreakTableEntity.setBreakLogId(breakLogId);
            ruleBreakTableEntity.setAdminUId(adminUid);
            ruleBreakTableEntity.setBreakUId(breakUid);
            ruleBreakTableEntity.setDecribe(decribe);
            ruleBreakTableEntity.setFlag(flag);
            list.add(ruleBreakTableEntity);
        }
        return list;
    }
}
