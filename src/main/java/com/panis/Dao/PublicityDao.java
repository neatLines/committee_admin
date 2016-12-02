package com.panis.Dao;

import com.panis.model.PublicityTableEntity;

import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public interface PublicityDao {
    public List<PublicityTableEntity> findAll() throws Exception;

    public List<PublicityTableEntity> findOrderByPId(Integer pId) throws Exception;

}
