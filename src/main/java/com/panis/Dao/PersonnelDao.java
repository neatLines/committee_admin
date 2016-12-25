package com.panis.Dao;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public interface PersonnelDao extends BaseDao{
    public void changeDuty(int u_id, String duty) throws Exception;

    public void insert(int u_id, String duty) throws Exception;

    public void changeUserPower(int u_id, int power) throws Exception;

}
