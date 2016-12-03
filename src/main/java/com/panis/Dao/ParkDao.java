package com.panis.Dao;

import com.panis.model.ParkTableEntity;

import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public interface ParkDao extends BaseDao{

    public List<ParkTableEntity> findOrderByUId(Integer uId) throws Exception;

    public List<ParkTableEntity> findOrderByParkId(Integer parkId) throws Exception;
}
