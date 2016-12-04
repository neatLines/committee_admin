package com.panis.Dao;

import com.panis.model.RuleBreakTableEntity;

import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public interface RuleBreakDao extends BaseDao {
    public List findOrderByBreakUId(int breakUId) throws Exception;

    public List findAllLinkUserAndAdmin() throws Exception;

    public void flush();
}
