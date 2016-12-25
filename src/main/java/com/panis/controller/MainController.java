package com.panis.controller;

import com.panis.Dao.UserDao;
import com.panis.DaoImpl.UserDaoImpl;
import com.panis.model.UserTableEntity;
import com.panis.util.Md5Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
        model.addAttribute("role","1");
        return "login";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
        return "test";
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
    public Object getJSON(@RequestBody UserTableEntity userTableEntity, Model model) {
        System.out.println(userTableEntity.toString());
        System.out.println(userTableEntity.getUserName());
        List<UserTableEntity> dataList = null;
        try {
            dataList = userDao.findOrderByUserName(userTableEntity.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Md5Util md5Util = new Md5Util();

        if (dataList.isEmpty()) {
            return "{\"info\":\"fail\"}";
        }
        if ((md5Util.parseStrToMd5L16(userTableEntity.getPassword()+userTableEntity.getUserName())).equals(dataList.get(0).getPassword())){
            model.addAttribute("userCode", Integer.toString(dataList.get(0).getuId()));
            model.addAttribute("role", Integer.toString(dataList.get(0).getPower()));
            if (dataList.get(0).getPower()==0) {
                return "{\"info\":\"user\"}";
            } else if (dataList.get(0).getPower()==1) {
                return "{\"info\":\"admin\"}";
            } else if (dataList.get(0).getPower()==2) {
                return "{\"info\":\"superAdmin\"}";
            }
        }
        return "{\"info\":\"fail\"}";
    }

    @RequestMapping(value = "/logoutp",method = RequestMethod.POST)
    @ResponseBody
    public Object logout(HttpSession session) {
        try {
            session.removeAttribute("userCode");
            session.removeAttribute("role");
        } catch (Exception e) {
            return "{\"info\":\"fail\"}";
        }
        return "{\"info\":\"success\"}";
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
            return "{\"info\":\"success\"}";
        } else {
            return "{\"info\":\"fail\"}";
        }
    }
    /**
     * 显示个人信息
     * @return
     */

    @RequestMapping(value = "/displayselfinfo" ,method = RequestMethod.GET)
    public String displayInfo() {
        return "displayselfinfo";
    }

    /**
     * 修改展示页面
     * @return
     */
    @RequestMapping(value = "/changeselfinfo" ,method = RequestMethod.GET)
    public String changeInfo() {
        return "changeselfinfo";
    }

    /**
     * 显示人事信息
     * @return
     */
    @RequestMapping(value = "/displaypersonalinfo" ,method = RequestMethod.GET)
    public String displaypInfo() {
        return "displaypersonalinfo";
    }

    /**
     * 显示用户信息
     * @return
     */
    @RequestMapping(value = "/displayuserinfo" ,method = RequestMethod.GET)
    public String displayuInfo() {
        return "displayuserinfo";
    }

    /**
     * 修改人事信息
     * @return
     */
    @RequestMapping(value = "/changepersonalinfo" ,method = RequestMethod.GET)
    public String changepInfo() {
        return "changepersonalinfo";
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/change" ,method = RequestMethod.GET)
    public String change() {
        return "change";
    }

    /**
     * 修改1
     * @return
     */
    @RequestMapping(value = "/change1" ,method = RequestMethod.GET)
    public String change1() {
        return "change1";
    }

    /**
     * 主页？
     * @return
     */
    @RequestMapping(value = "/selfmanage" ,method = RequestMethod.GET)
    public String selfm() {
        return "selfmanage";
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "/changepassword" ,method = RequestMethod.GET)
    public String changep() {
        return "changepassword";
    }

    /**
     * 主页？
     * @return
     */
    @RequestMapping(value = "/selfmanage1" ,method = RequestMethod.GET)
    public String selfm1() {
        return "selfmanage1";
    }

    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/parkPage" ,method = RequestMethod.GET)
    public String pkg() {
        return "parkPage";
    }

    /**
     * 删除用户信息
     * @return
     */
    @RequestMapping(value = "/change2" ,method = RequestMethod.GET)
    public String change2() {
        return "change2";
    }

    @RequestMapping(value = "/changeConsult" ,method = RequestMethod.GET)
    public String changeC() {
        return "changeConsult";
    }

    @RequestMapping(value = "/changeHousePage" ,method = RequestMethod.GET)
    public String changeH() {
        return "changeHousePage";
    }

    @RequestMapping(value = "/changeParkPage" ,method = RequestMethod.GET)
    public String changeP() {
        return "changeParkPage";
    }

    @RequestMapping(value = "/changePeccancyPage" ,method = RequestMethod.GET)
    public String changePe() {
        return "changePeccancyPage";
    }

    @RequestMapping(value = "/changeRichesPage" ,method = RequestMethod.GET)
    public String changeR() {
        return "changeRichesPage";
    }

    @RequestMapping(value = "/consultPage" ,method = RequestMethod.GET)
    public String consultP() {
        return "consultPage";
    }

    @RequestMapping(value = "/housePage" ,method = RequestMethod.GET)
    public String houseP() {
        return "housePage";
    }

    @RequestMapping(value = "/insertConsultPage" ,method = RequestMethod.GET)
    public String insertC() {
        return "insertConsultPage";
    }

    @RequestMapping(value = "/insertPeccancyPage" ,method = RequestMethod.GET)
    public String insertP() {
        return "insertPeccancyPage";
    }

    @RequestMapping(value = "/insertPublicFacPage" ,method = RequestMethod.GET)
    public String insertPu() {
        return "insertPublicFacPage";
    }

    @RequestMapping(value = "/insertRichesPage" ,method = RequestMethod.GET)
    public String insertRi() {
        return "insertRichesPage";
    }

    @RequestMapping(value = "/mainPage" ,method = RequestMethod.GET)
    public String mainP() {
        return "mainPage";
    }

    @RequestMapping(value = "/peccancyPage" ,method = RequestMethod.GET)
    public String peccancyP() {
        return "peccancyPage";
    }

    @RequestMapping(value = "/publicFacPage" ,method = RequestMethod.GET)
    public String publicF() {
        return "publicFacPage";
    }

    @RequestMapping(value = "/publicFacPageInfo" ,method = RequestMethod.GET)
    public String publicFa() {
        return "publicFacPageInfo";
    }

    @RequestMapping(value = "/richesPage" ,method = RequestMethod.GET)
    public String richesPage() {
        return "richesPage";
    }

    @RequestMapping(value = "/singleIfo" ,method = RequestMethod.GET)
    public String singleIfo() {
        return "singleIfo";
    }
}
