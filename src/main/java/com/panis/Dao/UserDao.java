package com.panis.Dao;

import com.panis.model.UserTableEntity;

import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public interface UserDao extends BaseDao {
    public List<UserTableEntity> findOrderByUserName(String userName) throws Exception;
}

