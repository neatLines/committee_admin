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

    private HouseDao houseDao;

    private ParkDao parkDao;

    private PersonnelDao personnelDao;

    private PublicityDao publicityDao;

    private RuleBreakDao ruleBreakDao;

    private UserDao userDao;

    public UserAdminController() {
        super();
        houseDao = new HouseDaoImpl();
        parkDao = new ParkDaoImpl();
        personnelDao = new PersonnelDaoImpl();
        publicityDao = new PublicityDaoImpl();
        ruleBreakDao = new RuleBreakDaoImpl();
        userDao = new UserDaoImpl();
    }



    /**
     * json接口
     * 得到所有的违章记录以及违章记录的录入者以及违章人员
     * 返回json
     * 无权限认证
     * @param session
     * @return
     */
    @RequestMapping(value = "/json/getAllBreakLog", method = RequestMethod.GET)
    public @ResponseBody List getAllBreakLog (HttpSession session) {
        List<RuleBreakTableEntity> ruleBreakTableEntities = null;
        try {
            ruleBreakTableEntities = ruleBreakDao.findAllLinkUserAndAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruleBreakTableEntities;
    }

    /**
     * json接口
     * 得到当前用户的所有违章记录以及违章记录的录入者
     * 返回json
     * 权限认证（带修改）
     * todo 修改增加错误返回
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/json/getMinBreakLog", method = RequestMethod.GET)
    public @ResponseBody List getBreakLog (HttpSession session,Model model) {
        List ruleBreakTableEntities = null;
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

    /**
     * json接口
     * 设置已读
     * 传入json对象数组
     * 传入session
     * 验证有无修改权限
     * 返回success或fail或提示登陆
     * todo 后期改为返回页面
     * @param ruleBreakTableEntity
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/json/setHadRead", method = RequestMethod.POST)
    @ResponseBody
    public Object setHadWrite (@RequestBody List<RuleBreakTableEntity> ruleBreakTableEntity,HttpSession session,Model model) {
        String u_id;
        try {
            u_id = (String) session.getAttribute("userCode");
        } catch (NullPointerException np) {
            return "{\"info\":\"permission denied\"}";
        }
        List list = new ArrayList();
        int count = 0;
        try {
            for (int i = 0; i < ruleBreakTableEntity.size(); i++) {
                if (ruleBreakTableEntity.get(i).getBreakUId() == Integer.parseInt(u_id)) {
                    ruleBreakTableEntity.get(i).setFlag(true);
                    list.add(ruleBreakTableEntity);
                    count++;
                }
            }
        } catch (Exception e) {
            return "{\"info\":\"你还没有登陆\"}";
        }
        try {
            ruleBreakDao.updateById(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ruleBreakTableEntity.size()==count) {
            return "{\"info\":\"success\"}";
        } else {
            return "{\"info\":\"fail\"}";
        }
    }

    /**
     * json接口
     * 得到个人信息
     * 无数入
     * 返回当前登陆的账号信息
     * @param session
     * @return
     */

    @RequestMapping(value = "/json/getMinInfo",method = RequestMethod.GET)
    @ResponseBody
    public Object getMinInfo(HttpSession session) {
        UserTableEntity userTableEntity = null;
        try {
            userTableEntity = (UserTableEntity) userDao.findById(Integer.valueOf((String) session.getAttribute("userCode"))).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userTableEntity==null) {
            return "{\"info\":\"你还没有登陆\"}";
        }
        return userTableEntity;
    }



    /**
     * json接口
     * 修改个人信息
     * 传入userTableEntity，session
     * 验证有无修改权限
     * 返回success或fail或提示登陆
     * todo 后期改为返回页面
     * @param userTableEntity
     * @param session
     * @return
     */
    @RequestMapping(value = "/json/changeInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object changeInfo(@RequestBody UserTableEntity userTableEntity,HttpSession session) {
        UserTableEntity userTableEntity1;
        try {
            userTableEntity1 = (UserTableEntity) userDao.findById(Integer.valueOf((String) session.getAttribute("userCode"))).get(0);
        } catch (Exception e) {
            return "{\"info\":\"你还没有登陆\"}";
        }
        if (userTableEntity.getUserName().equals(userTableEntity1.getUserName())) {
            userTableEntity.setuId(userTableEntity1.getuId());
            userTableEntity.setPower(userTableEntity1.getPower());
            Md5Util md5Util = new Md5Util();

            userTableEntity.setPassword(md5Util.parseStrToMd5L16(userTableEntity.getPassword()+userTableEntity.getUserName()));
            try {
                List list = new ArrayList();
                list.add(userTableEntity);
                userDao.updateById(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "{\"info\":\"success\"}";
        }
        return "{\"info\":\"fail\"}";
    }

    // TODO: 29/11/2016 增加错误提示页面代替tomcat自带提示

    /**
     * json接口
     * 修改住房信息
     * 传入houseTableEntity
     * 验证有无修改权限
     * 返回success或fail或提示登陆
     * todo 后期改为返回页面
     * @param houseTableEntity
     * @param session
     * @return
     */
    @RequestMapping(value = "/json/changeHouseInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object changeHouseInfo(@RequestBody HouseTableEntity houseTableEntity,HttpSession session) {
        UserTableEntity userTableEntity;
        try {
            UserTableEntity temp = new UserTableEntity();
            temp.setuId(Integer.valueOf((String) session.getAttribute("userCode")));
            userTableEntity = (UserTableEntity) userDao.findById(temp).get(0);
        } catch (Exception e) {
            return "{\"info\":\"你还没有登陆\"}";
        }
//        UserTableEntity userTableEntity = userRepository.findOrderByUId(1).get(0);
        HouseTableEntity houseTableEntity1 = null;
        try {
            houseTableEntity1 = (HouseTableEntity) houseDao.findById(houseTableEntity).get(0);
            if (houseTableEntity1.getuId().equals(userTableEntity.getuId())) {
                List list = new ArrayList();
                list.add(houseTableEntity);
                houseDao.updateById(list);
                return "{\"info\":\"success\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"info\":\"fail\"}";
    }

    /**
     * 开放的json接口
     * 得到所有公告
     * 无验证
     * 返回json对象数组
     * @return
     */
    @RequestMapping(value = "/json/getAllPublicity", method = RequestMethod.GET)
    public @ResponseBody List getAllPublic() {
        List publicityTableEntities = null;
        try {
            publicityTableEntities = publicityDao.findAll(PublicityTableEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicityTableEntities;
    }

    /**
     * 开放的json接口
     * 得到公告详情
     * 传入publicityTableEntity
     * 得到完整的publicityTableEntity json对象
     * @param publicityTableEntity
     * @return
     */
    @RequestMapping(value = "json/getPublicityDetail", method = RequestMethod.POST)
    @ResponseBody
    public  PublicityTableEntity getDetail(@RequestBody PublicityTableEntity publicityTableEntity){
        List<PublicityTableEntity> publicityTableEntities = null;
        try {
            publicityTableEntities = publicityDao.findById(publicityTableEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicityTableEntities.get(0);
    }

    /**
     * 开放的json接口
     * 得到所有人事信息
     * 无验证
     * 返回所有人事信息
     * @return
     */
    @RequestMapping(value = "/json/getPersonnelOnlyRead", method = RequestMethod.GET)
    @ResponseBody
    public List<PersonnelTableEntity> getPersonnelOnlyRead() {
        try {
            return personnelDao.findAllLinkUserTable(PersonnelTableEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json接口
     * 得到自己拥有的停车位信息
     * 传入session
     * 验证用户信息
     * 返回json对象
     * @param session
     * @return
     */
    @RequestMapping(value = "/json/getOwnParkInfo", method = RequestMethod.GET)
    @ResponseBody
    public List<ParkTableEntity> getOwnPark(HttpSession session) {
        try {
            return parkDao.findOrderByUId(Integer.valueOf((String) session.getAttribute("userCode")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
