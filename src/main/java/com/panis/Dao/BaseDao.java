package com.panis.Dao;


import java.util.List;

/**
 * Created by fuyipeng on 02/12/2016.
 */
public interface BaseDao {
    public List findAll(Class cl) throws Exception;

    public boolean insert(Object object) throws Exception;

    public List findById(Object object) throws Exception;

    public boolean updateById(List<Object> objects) throws Exception;

    public boolean delete(List<Object> objects) throws Exception;

    public List findAllLinkUserTable(Class cl) throws Exception;

    public boolean flushTest();
}
