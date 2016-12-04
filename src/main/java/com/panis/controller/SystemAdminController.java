package com.panis.controller;

import com.panis.Dao.UserDao;
import com.panis.DaoImpl.UserDaoImpl;
import com.panis.model.UserTableEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by fuyipeng on 04/12/2016.
 */
@Controller
@SessionAttributes({"userCode","role"})
public class SystemAdminController {

    UserDao userDao;

    public SystemAdminController() {
        super();
        userDao = new UserDaoImpl();
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
                return userDao.findAll(UserTableEntity.class);
            } catch (Exception e) {
//                e.printStackTrace();
                return "{\"info\":\"query fail\"}";
            }
        }
    }
}
