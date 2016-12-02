package com.panis.Dao;

import com.panis.model.RuleBreakTableEntity;

import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public interface RuleBreakDao {
    public List<RuleBreakTableEntity> findAll() throws Exception;

    public List<RuleBreakTableEntity> findOrderByBreakUId(int breakUId) throws Exception;

    public boolean updateRuleBreakTable(Integer adminUId, Integer breakUId,
                                        String decribe, Byte flag, Integer breakLogId) throws Exception;

    public void flush();
}
