package com.panis.controller;

import com.panis.Dao.UserDao;
import com.panis.DaoImpl.UserDaoImpl;
import com.panis.model.UserTableEntity;
import com.panis.util.Md5Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fuyipeng on 2016/11/8.
 */
@Controller
@SessionAttributes({"userCode","role"})
public class MainController {

    UserDao userDao;

    public MainController() {
        super();
        userDao = new UserDaoImpl();
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("userCode","1");
        model.addAttribute("role","2");
        return "login";
    }

    @RequestMapping(value = "/loginp", method = RequestMethod.POST)
    @ResponseBody
    public String getJSON(@RequestBody UserTableEntity userTableEntity, Model model) {
        List<UserTableEntity> dataList = null;
        try {
            dataList = userDao.findOrderByUserName(userTableEntity.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Md5Util md5Util = new Md5Util();

        if (dataList.isEmpty()) {
            return "login";
        }
        if (md5Util.parseStrToMd5L16(userTableEntity.getPassword()+userTableEntity.getUserName()).equals(dataList.get(0).getPassword())){
            model.addAttribute("userCode", Integer.toString(userTableEntity.getuId()));
            model.addAttribute("role",dataList.get(0).getPower());
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/register" ,method = RequestMethod.GET)
    public String addUser() {
        return "register";
    }

    @RequestMapping(value = "/registerp",method = RequestMethod.POST)
    @ResponseBody
    public String addUserPost(@RequestBody UserTableEntity userTableEntity) {
        // post请求传递过来的是一个UserTableEntity对象，里面包含了该用户
        /*
            private String uName;
            private int uAge;
            private byte uSex;
            private String phoneNumber;
            private String userName;
            private String password;
         */
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象
        System.out.println(userTableEntity.getuName()+userTableEntity.getUserName()+userTableEntity.getPassword());

        Md5Util md5Util = new Md5Util();
        userTableEntity.setPassword(md5Util.parseStrToMd5L16(userTableEntity.getPassword()+userTableEntity.getUserName()));
        userTableEntity.setPower(0);
        boolean insert = false;
        // 数据库中添加一个用户，并立即刷新缓存
        try {
            insert = userDao.insert(userTableEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 重定向到登陆页面
        if (insert) {
            return "redirect:/login";
        } else {
            return "{\"info\":\"fail\"}";
        }
    }
}
