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

/**
 * 普通用户的登陆类
 * session以userCode和role保存u_id和power
 * 负责拦截/,/login,/loginp,/register,/registerp
 */
@Controller
@SessionAttributes({"userCode","role"})
public class MainController {

    UserDao userDao;

    public MainController() {
        super();
        userDao = new UserDaoImpl();
    }

    /**
     * 返回首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    /**
     * 登陆页面---带修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("userCode","1");
        model.addAttribute("role","2");
        return "login";
    }

    /**
     * 接受userTableEntity中的password和username
     * 完成登陆验证
     * 设置session中userCode为u_id
     * 设置session中role为power
     * @param userTableEntity
     * @param model
     * @return
     */
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

    /**
     * 返回注册页面
     * @return
     */
    @RequestMapping(value = "/register" ,method = RequestMethod.GET)
    public String addUser() {
        return "register";
    }

    /**
     * 接受一个u_id缺省的userTableEntity
     * 使用Md5加密密码
     * 添加用户
     * 重定向至登陆界面
     * @param userTableEntity
     * @return
     */
    @RequestMapping(value = "/registerp",method = RequestMethod.POST)
    @ResponseBody
    public String addUserPost(@RequestBody UserTableEntity userTableEntity) {
        // post请求传递过来的是一个UserTableEntity对象，里面包含了该用户
        /*
            uName;
            uAge;
            uSex;
            phoneNumber;
            userName;
            password;
         */
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象
        System.out.println(userTableEntity.getuName()+userTableEntity.getUserName()+userTableEntity.getPassword());

        Md5Util md5Util = new Md5Util();
        userTableEntity.setPassword(md5Util.parseStrToMd5L16(userTableEntity.getPassword()+userTableEntity.getUserName()));
        userTableEntity.setPower(0);
        boolean insert = false;
        // 数据库中添加一个用户
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
