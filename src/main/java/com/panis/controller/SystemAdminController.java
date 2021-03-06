package com.panis.controller;

import com.panis.Dao.PersonnelDao;
import com.panis.Dao.UserDao;
import com.panis.DaoImpl.PersonnelDaoImpl;
import com.panis.DaoImpl.UserDaoImpl;
import com.panis.model.PersonnelTableEntity;
import com.panis.model.UserTableEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fuyipeng on 04/12/2016.
 */
@Controller
@SessionAttributes({"userCode","role"})
public class SystemAdminController {

    UserDao userDao;

    PersonnelDao personnelDao;
    public SystemAdminController() {
        super();
        userDao = new UserDaoImpl();
        personnelDao = new PersonnelDaoImpl();
    }


    /**
     * 系统管理员获取所有用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "/json/getAllUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object superGetUserInfo(HttpSession session) {
        String temp = null;
        try {
            temp = (String) session.getAttribute("role");
        } catch (Exception e) {
            return "{\"info\":\"permission denied\"}";
        }
        if (!"2".equals(temp)&&!"1".equals(temp)) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                return userDao.findAllLinkPersonnel();
            } catch (Exception e) {
//                e.printStackTrace();
                return "{\"info\":\"query fail\"}";
            }
        }
    }

    @RequestMapping(value = "/json/changeUserPowerAndDuty", method = RequestMethod.POST)
    @ResponseBody
    public Object superChangeUserInfo(@RequestBody Map map, HttpSession session) {
        String temp = null;
        try {
            temp = (String) session.getAttribute("role");
        } catch (Exception e) {
            return "{\"info\":\"permission denied\"}";
        }
        if (!"2".equals(temp)) {
            return "{\"info\":\"permission denied\"}";
        }
        int uId = Integer.valueOf((String) map.get("uId"));
        int power = Integer.valueOf((String) map.get("power"));
        String duty = (String) map.get("duty");
        System.out.println(uId+" "+power+" "+duty);
        UserTableEntity userTableEntity = new UserTableEntity();
        userTableEntity.setuId(uId);
        try {
            if (userDao.findById(userTableEntity).size()<1) {
                return "{\"info\":\"object not found\"}";
            }
        } catch (Exception e) {
            return "{\"info\":\"object not found\"}";
        }
        try {
            PersonnelTableEntity personnelTableEntity = new PersonnelTableEntity();
            personnelTableEntity.setDuty(duty);
            personnelTableEntity.setuId(uId);

            if (personnelDao.findById(personnelTableEntity).size()<1) {
                personnelDao.changeUserPower(uId,power);
                personnelDao.insert(uId,duty);
                if (personnelDao.flushTest()) {
                    return "{\"info\":\"insert success\"}";
                } else {
                    return "{\"info\":\"哎呀没办法更新\"}";
                }
            } else {
                personnelDao.changeUserPower(uId,power);
                personnelDao.changeDuty(uId,duty);
                if (personnelDao.flushTest()) {
                    return "{\"info\":\"update success\"}";
                } else {
                    return "{\"info\":\"哎呀没办法更新\"}";
                }
            }
        } catch (Exception e) {
            return "{\"info\":\"出了一些偏差\"}";
        }
    }

    @RequestMapping(value = "/json/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteUser(@RequestBody UserTableEntity userTableEntity, HttpSession session) {
        String temp = null;
        try {
            temp = (String) session.getAttribute("role");
        } catch (Exception e) {
            return "{\"info\":\"permission denied\"}";
        }
        if (!"2".equals(temp)&&!"1".equals(temp)) {
            return "{\"info\":\"permission denied\"}";
        }
        try {
            List list = new ArrayList();
            list.add(userTableEntity);
            userDao.delete(list);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"info\":\"something happened\"}";
        }
        return "{\"info\":\"delete success\"}";
    }


    @RequestMapping(value = "/json/getUserInfoOrderByUserName", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserInfoByUserName(@RequestBody Map map, HttpSession session) {
        System.out.println("...");
        String temp = null;
        try {
            temp = (String) session.getAttribute("role");
        } catch (Exception e) {
            return "{\"info\":\"permission denied\"}";
        }
        if (!"2".equals(temp)) {
            return "{\"info\":\"permission denied\"}";
        }
        System.out.println(".....");
        try{
            return userDao.findOrderByUserName(map.get("userName").toString()).get(0);
        } catch (Exception e) {
            return "{\"info\":\"something happened\"}";
        }
    }

    @RequestMapping(value = "/json/getUserInfoOrderByUId", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserInfoByUId(@RequestBody Map map, HttpSession session) {
        System.out.println("...");
        String temp = null;
        try {
            temp = (String) session.getAttribute("role");
        } catch (Exception e) {
            return "{\"info\":\"permission denied\"}";
        }
        if (!"2".equals(temp)) {
            return "{\"info\":\"permission denied\"}";
        }
        System.out.println(".....");
        try{
            UserTableEntity userTableEntity = new UserTableEntity();
            userTableEntity.setuId(Integer.valueOf((String) map.get("uId")));
            return (UserTableEntity)userDao.findById(userTableEntity).get(0);
        } catch (Exception e) {
            return "{\"info\":\"something happened\"}";
        }
    }
}
