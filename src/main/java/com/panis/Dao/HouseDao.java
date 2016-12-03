package com.panis.Dao;

import com.panis.model.HouseTableEntity;

import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public interface HouseDao extends BaseDao {

    public List<HouseTableEntity> findOrderByHouseId(int houseId) throws Exception;

    public boolean updateHouseInfo(Integer uId, String apHouse,
                                   String pannant, String state,
                                   int houseId) throws Exception;

}
