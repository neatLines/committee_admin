package com.panis.controller;

import com.panis.model.*;
import com.panis.repository.*;
import com.panis.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by fuyipeng on 28/11/2016.
 */
@Controller
@SessionAttributes({"userCode","role"})
public class UserAdminController {
    @Autowired
    RuleBreakRepository ruleBreakRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    PublicityRepository publicityRepository;

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    ParkRepository parkRepository;
    //// TODO: 29/11/2016 session得到userCode未测试

    @RequestMapping(value = "/json/getMinBreakLog", method = RequestMethod.GET)
    public @ResponseBody List<RuleBreakTableEntity> getBreakLog (HttpSession session,Model model) {
        List<RuleBreakTableEntity> ruleBreakTableEntities = ruleBreakRepository.findOrderByBreakUId(Integer.parseInt((String) session.getAttribute("userCode")));
        return ruleBreakTableEntities;
    }
//// TODO: 29/11/2016 session得到userCode未测试
    @RequestMapping(value = "/json/setHadRead", method = RequestMethod.POST)
    @ResponseBody
    public Object setHadWrite (@RequestBody List<RuleBreakTableEntity> ruleBreakTableEntity,HttpSession session,Model model) {
        int count = 0;
        try {
            for (int i = 0; i < ruleBreakTableEntity.size(); i++) {
                if (ruleBreakTableEntity.get(i).getBreakUId() == Integer.parseInt((String) session.getAttribute("userCode"))) {
                    ruleBreakTableEntity.get(i).setFlag((byte) 1);
                    ruleBreakRepository.updateRuleBreakTable(ruleBreakTableEntity.get(i).getAdminUId(),
                            ruleBreakTableEntity.get(i).getBreakUId(), ruleBreakTableEntity.get(i).getDecribe(),
                            ruleBreakTableEntity.get(i).getFlag(), ruleBreakTableEntity.get(i).getBreakLogId());
                    ruleBreakRepository.flush();
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
//// TODO: 29/11/2016 session得到userCode未测试
    @RequestMapping(value = "/json/changeInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object changeInfo(@RequestBody UserTableEntity userTableEntity,HttpSession session) {
        UserTableEntity userTableEntity1;
        try {
            userTableEntity1 = userRepository.findOrderByUId(Integer.valueOf((String) session.getAttribute("userCode"))).get(0);
        } catch (Exception e) {
            return "{\"info\":\"你还没有登陆\"}";

        }
        if (userTableEntity.getUserName().equals(userTableEntity1.getUserName())) {
            userTableEntity.setPower(userTableEntity1.getPower());
            Md5Util md5Util = new Md5Util();
            userTableEntity.setPassword(md5Util.parseStrToMd5L16(userTableEntity.getPassword()+userTableEntity.getUserName()));
            userRepository.updateUserInfo(userTableEntity.getuName(),userTableEntity.getuAge(),
                    userTableEntity.getuSex(),userTableEntity.getPhoneNumber(),
                    userTableEntity.getUserName(),userTableEntity.getPassword(),
                    userTableEntity.getPower(),userTableEntity1.getuId());
            userRepository.flush();
            return "{\"info\":\"success\"}";
        }
        return "{\"info\":\"fail\"}";
    }

    //// TODO: 29/11/2016 session得到userCode未测试
    // TODO: 29/11/2016 增加错误提示页面代替tomcat自带提示
    @RequestMapping(value = "/json/changeHouseInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object changeHouseInfo(@RequestBody HouseTableEntity houseTableEntity,HttpSession session) {
        UserTableEntity userTableEntity;
        try {
            userTableEntity = userRepository.findOrderByUId(Integer.valueOf((String) session.getAttribute("userCode"))).get(0);
        } catch (Exception e) {
            return "{\"info\":\"你还没有登陆\"}";
        }
//        UserTableEntity userTableEntity = userRepository.findOrderByUId(1).get(0);
        HouseTableEntity houseTableEntity1 = houseRepository.findOrderByHouseId(houseTableEntity.getHouseId()).get(0);
        if (houseTableEntity1.getuId().equals(userTableEntity.getuId())) {
            houseRepository.updateHouseInfo(houseTableEntity1.getuId(),houseTableEntity1.getApHouse(),
                    houseTableEntity1.getPannant(),houseTableEntity.getState(),
                    houseTableEntity1.getHouseId());
            userRepository.flush();
            return "{\"info\":\"success\"}";
        }
        return "{\"info\":\"fail\"}";
    }

    @RequestMapping(value = "/json/getAllPublicity", method = RequestMethod.GET)
    public @ResponseBody List<PublicityTableEntity> getAllPublic() {
        List<PublicityTableEntity> publicityTableEntities =  publicityRepository.findAll();
        for (int i = 0;i < publicityTableEntities.size();i++) {
            publicityTableEntities.get(i).setDetail("");
        }
        return publicityTableEntities;
    }

    @RequestMapping(value = "json/getPublicityDetail", method = RequestMethod.POST)
    @ResponseBody
    public  PublicityTableEntity getDetail(@RequestBody PublicityTableEntity publicityTableEntity){
        List<PublicityTableEntity> publicityTableEntities = publicityRepository.findOrderByPId(publicityTableEntity.getpId());
        return publicityTableEntities.get(0);
    }

    @RequestMapping(value = "/json/getPersonnelOnlyRead", method = RequestMethod.GET)
    @ResponseBody
    public List<PersonnelTableEntity> getPersonnelOnlyRead() {
        return personnelRepository.findAll();
    }

    @RequestMapping(value = "/json/getOwnParkInfo", method = RequestMethod.GET)
    @ResponseBody
    public List<ParkTableEntity> getOwnPark(HttpSession session) {
        return parkRepository.findOrderByUId(1);
    }

    @RequestMapping(value="/check", method= RequestMethod.GET)
    public String check (HttpServletRequest request, HttpSession session) {

        System.out.println("");
        System.out.println("");
        System.out.println("inside check");

        System.out.println("");
        System.out.println("*** Session data ***");
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String s = e.nextElement();
            System.out.println(s + " == " + session.getAttribute(s));
        }
        return "test";
    }
}
