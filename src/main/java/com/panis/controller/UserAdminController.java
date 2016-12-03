package com.panis.controller;

import com.panis.Dao.*;
import com.panis.DaoImpl.*;
import com.panis.model.*;
import com.panis.repository.*;
import com.panis.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by fuyipeng on 28/11/2016.
 */
@Controller
@SessionAttributes({"userCode","role"})
public class UserAdminController {

    HouseDao houseDao;

    ParkDao parkDao;

    PersonnelDao personnelDao;

    PublicityDao publicityDao;

    RuleBreakDao ruleBreakDao;

    UserDao userDao;

    public UserAdminController() {
        super();
        houseDao = new HouseDaoImpl();
        parkDao = new ParkDaoImpl();
        personnelDao = new PersonnelDaoImpl();
        publicityDao = new PublicityDaoImpl();
        ruleBreakDao = new RuleBreakDaoImpl();
        userDao = new UserDaoImpl();
    }

    // TODO: 29/11/2016 session得到userCode未测试

    @RequestMapping(value = "/json/getAllBreakLog", method = RequestMethod.GET)
    public @ResponseBody List<RuleBreakTableEntity> getAllBreakLog (HttpSession session) {
        List<RuleBreakTableEntity> ruleBreakTableEntities = null;
        try {
            ruleBreakTableEntities = ruleBreakDao.findAll(RuleBreakTableEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruleBreakTableEntities;
    }

    @RequestMapping(value = "/json/getMinBreakLog", method = RequestMethod.GET)
    public @ResponseBody List<RuleBreakTableEntity> getBreakLog (HttpSession session,Model model) {
        List<RuleBreakTableEntity> ruleBreakTableEntities = null;
        try {
            ruleBreakTableEntities = ruleBreakDao.findOrderByBreakUId(Integer.parseInt((String) session.getAttribute("userCode")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruleBreakTableEntities;
    }
    /*
     *标记违章记录是否被阅读
     * /json/setHadRead
     * List<RuleBreakTableEntity>
     * json{info:....}
     * 验证用户是否登陆，确定修改权限
     */

    @RequestMapping(value = "/json/setHadRead", method = RequestMethod.POST)
    @ResponseBody
    public Object setHadWrite (@RequestBody List<RuleBreakTableEntity> ruleBreakTableEntity,HttpSession session,Model model) {
        int count = 0;
        try {
            for (int i = 0; i < ruleBreakTableEntity.size(); i++) {
                if (ruleBreakTableEntity.get(i).getBreakUId() == Integer.parseInt((String) session.getAttribute("userCode"))) {
                    ruleBreakTableEntity.get(i).setFlag((byte) 1);
                    ruleBreakDao.updateRuleBreakTable(ruleBreakTableEntity.get(i).getAdminUId(),
                            ruleBreakTableEntity.get(i).getBreakUId(), ruleBreakTableEntity.get(i).getDecribe(),
                            ruleBreakTableEntity.get(i).getFlag(), ruleBreakTableEntity.get(i).getBreakLogId());
                    ruleBreakDao.flush();
                    count++;
                }
            }
        } catch (Exception e) {
            return "{\"info\":\"你还没有登陆\"}";
        }
        if (ruleBreakTableEntity.size()==count) {
            return "{\"info\":\"success\"}";
        } else {
            return "{\"info\":\"fail\"}";
        }
    }
    /*
     *修改个人信息
     * /json/changeInfo
     * UserTableEntity
     * session
     * 验证用户修改权限
     */
    @RequestMapping(value = "/json/changeInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object changeInfo(@RequestBody UserTableEntity userTableEntity,HttpSession session) {
        UserTableEntity userTableEntity1;
        try {
            userTableEntity1 = userDao.findOrderByUId(Integer.valueOf((String) session.getAttribute("userCode"))).get(0);
        } catch (Exception e) {
            return "{\"info\":\"你还没有登陆\"}";

        }
        if (userTableEntity.getUserName().equals(userTableEntity1.getUserName())) {
            userTableEntity.setPower(userTableEntity1.getPower());
            Md5Util md5Util = new Md5Util();
            userTableEntity.setPassword(md5Util.parseStrToMd5L16(userTableEntity.getPassword()+userTableEntity.getUserName()));
            try {
                userDao.updateUserInfo(userTableEntity.getuName(),userTableEntity.getuAge(),
                        userTableEntity.getuSex(),userTableEntity.getPhoneNumber(),
                        userTableEntity.getUserName(),userTableEntity.getPassword(),
                        userTableEntity.getPower(),userTableEntity1.getuId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            userDao.flush();
            return "{\"info\":\"success\"}";
        }
        return "{\"info\":\"fail\"}";
    }

    // TODO: 29/11/2016 增加错误提示页面代替tomcat自带提示
    @RequestMapping(value = "/json/changeHouseInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object changeHouseInfo(@RequestBody HouseTableEntity houseTableEntity,HttpSession session) {
        UserTableEntity userTableEntity;
        try {
            userTableEntity = userDao.findOrderByUId(Integer.valueOf((String) session.getAttribute("userCode"))).get(0);
        } catch (Exception e) {
            return "{\"info\":\"你还没有登陆\"}";
        }
//        UserTableEntity userTableEntity = userRepository.findOrderByUId(1).get(0);
        HouseTableEntity houseTableEntity1 = null;
        try {
            houseTableEntity1 = houseDao.findOrderByHouseId(houseTableEntity.getHouseId()).get(0);
            if (houseTableEntity1.getuId().equals(userTableEntity.getuId())) {
                houseDao.updateHouseInfo(houseTableEntity1.getuId(), houseTableEntity1.getApHouse(),
                        houseTableEntity1.getPannant(), houseTableEntity.getState(),
                        houseTableEntity1.getHouseId());
                userDao.flush();
                return "{\"info\":\"success\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"info\":\"fail\"}";
    }

    @RequestMapping(value = "/json/getAllPublicity", method = RequestMethod.GET)
    public @ResponseBody List<PublicityTableEntity> getAllPublic() {
        List<PublicityTableEntity> publicityTableEntities = null;
        try {
            publicityTableEntities = publicityDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0;i < publicityTableEntities.size();i++) {
            publicityTableEntities.get(i).setDetail("");
        }
        return publicityTableEntities;
    }

    @RequestMapping(value = "json/getPublicityDetail", method = RequestMethod.POST)
    @ResponseBody
    public  PublicityTableEntity getDetail(@RequestBody PublicityTableEntity publicityTableEntity){
        List<PublicityTableEntity> publicityTableEntities = null;
        try {
            publicityTableEntities = publicityDao.findOrderByPId(publicityTableEntity.getpId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicityTableEntities.get(0);
    }

    @RequestMapping(value = "/json/getPersonnelOnlyRead", method = RequestMethod.GET)
    @ResponseBody
    public List<PersonnelTableEntity> getPersonnelOnlyRead() {
        try {
            return personnelDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/json/getOwnParkInfo", method = RequestMethod.GET)
    @ResponseBody
    public List<ParkTableEntity> getOwnPark(HttpSession session) {
        try {
            return parkDao.findOrderByUId(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
