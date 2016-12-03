package com.panis.Dao;

import com.panis.model.RuleBreakTableEntity;

import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public interface RuleBreakDao extends BaseDao {
    public List<RuleBreakTableEntity> findOrderByBreakUId(int breakUId) throws Exception;

    public boolean updateRuleBreakTable(Integer adminUId, Integer breakUId,
                                        String decribe, Boolean flag, Integer breakLogId) throws Exception;

    public void flush();
}
