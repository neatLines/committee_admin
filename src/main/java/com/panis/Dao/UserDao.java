package com.panis.Dao;

import com.panis.model.UserTableEntity;

import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public interface UserDao {
    public List<UserTableEntity> findAll() throws Exception;

    public List<UserTableEntity> findOrderByUserName(String userName) throws Exception;

    public List<UserTableEntity> findOrderByUId(Integer uId) throws Exception;

    public boolean updateUserInfo (String uName,
                                   Integer uAge, String uSex,
                                   String phoneNumber, String userName,
                                   String password, byte power, Integer uId) throws Exception;
    public void flush();

    public boolean insert(UserTableEntity userTableEntity) throws Exception;
}

