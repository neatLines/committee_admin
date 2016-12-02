package com.panis.Dao;

import com.panis.model.PersonnelTableEntity;

import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public interface PersonnelDao {
    public List<PersonnelTableEntity> findAll() throws Exception;
}
