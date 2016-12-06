package com.panis.controller;

import com.panis.Dao.PersonnelDao;
import com.panis.Dao.UserDao;
import com.panis.DaoImpl.PersonnelDaoImpl;
import com.panis.DaoImpl.UserDaoImpl;
import com.panis.model.PersonnelTableEntity;
import com.panis.model.UserTableEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     * 系统管理员登陆
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/superLoginp", method = RequestMethod.POST)
    public String superLogin(Model model, HttpSession session) {
        if (((String)session.getAttribute("role")).equals("2")) {
            return "/superAdmin";
        } else {
            return "/";
        }
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
            System.out.println(temp);
        } catch (Exception e) {
            return "{\"info\":\"permission denied\"}";
        }
        if (!"2".equals(temp)) {
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

    @RequestMapping(value = "/json/changeUserPower", method = RequestMethod.POST)
    @ResponseBody
    public Object superChangeUserInfo(@RequestBody UserTableEntity userTableEntity, HttpSession session) {
        String temp = null;
        try {
            temp = (String) session.getAttribute("role");
        } catch (Exception e) {
            return "{\"info\":\"permission denied\"}";
        }
        if (!"2".equals(temp)) {
            return "{\"info\":\"permission denied\"}";
        }
        int power = userTableEntity.getPower();
        try {
            userTableEntity = (UserTableEntity) userDao.findById(userTableEntity.getuId()).get(0);
        } catch (Exception e) {
            return "{\"info\":\"object not found\"}";
        }
        userTableEntity.setPower(power);
        try {
            List list = new ArrayList();
            list.add(userTableEntity);
            if (userDao.updateById(list)) {
                return "{\"info\":\"success\"}";
            } else {
                return "{\"info\":\"哎呀没办法更新\"}";
            }
        } catch (Exception e) {
            return "{\"info\":\"出了一些偏差\"}";
        }
    }

    @RequestMapping(value = "/json/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteUser(@RequestBody UserTableEntity userTableEntity, HttpSession session) {
//        String temp = null;
//        try {
//            temp = (String) session.getAttribute("role");
//        } catch (Exception e) {
//            return "{\"info\":\"permission denied\"}";
//        }
//        if (!"2".equals(temp)) {
//            return "{\"info\":\"permission denied\"}";
//        }
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

    @RequestMapping(value = "/json/changeUserDuty", method = RequestMethod.POST)
    @ResponseBody
    public Object changeUserDuty(@RequestBody PersonnelTableEntity personnelTableEntity, HttpSession session) {
//        String temp = null;
//        try {
//            temp = (String) session.getAttribute("role");
//        } catch (Exception e) {
//            return "{\"info\":\"permission denied\"}";
//        }
//        if (!"2".equals(temp)) {
//            return "{\"info\":\"permission denied\"}";
//        }
        try {
            UserTableEntity tmp = new UserTableEntity();
            tmp.setuId(personnelTableEntity.getuId());
            if (userDao.findById(tmp).size()<1) {
                return "{\"info\":\"object not found\"}";
            }
            List list = new ArrayList();
            list.add(personnelTableEntity);
            if (personnelDao.updateById(list)) {
                return "{\"info\":\"update success\"}";
            } else {
                try {
                    if (personnelDao.insert(personnelTableEntity)) {
                        return "{\"info\":\"insert success\"}";
                    } else {
                        return "{\"info\":\"insert fail\"}";
                    }
                } catch (SQLException sqlE) {
                    return "{\"info\":\"insert fail\"}";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"info\":\"something happened\"}";
        }
    }
}
