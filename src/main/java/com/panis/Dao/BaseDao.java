package com.panis.Dao;


import java.util.List;
import java.util.Objects;

/**
 * Created by fuyipeng on 02/12/2016.
 */
public interface BaseDao {
    public List findAll(Class cl) throws Exception;

    public boolean insert(Object object) throws Exception;

    public List findById(Object object) throws Exception;

    public boolean updateById(Object object) throws Exception;

    public boolean delete(Object object) throws Exception;

    public List findAllLinkUserTable(Class cl) throws Exception;

}
