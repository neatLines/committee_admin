package com.panis.controller;

import com.panis.model.RuleBreakTableEntity;
import com.panis.model.UserTableEntity;
import com.panis.repository.RuleBreakRepository;
import com.panis.repository.UserRepository;
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
    @RequestMapping(value = "/json/getMinBreakLog", method = RequestMethod.GET)
    public @ResponseBody List<RuleBreakTableEntity> getBreakLog (HttpSession session,Model model) {
        List<RuleBreakTableEntity> ruleBreakTableEntities = ruleBreakRepository.findOrderByBreakUId(Integer.parseInt((String) session.getAttribute("userCode")));
        return ruleBreakTableEntities;
    }
//// TODO: 29/11/2016 未测试
    @RequestMapping(value = "/json/setHadRead", method = RequestMethod.POST)
    @ResponseBody
    public void setHadWrite (@RequestBody List<RuleBreakTableEntity> ruleBreakTableEntity,HttpSession session,Model model) {
        for (int i = 0;i < ruleBreakTableEntity.size();i++) {
            if (ruleBreakTableEntity.get(i).getBreakUId() == Integer.parseInt((String)session.getAttribute("userCode"))) {
                ruleBreakTableEntity.get(i).setFlag((byte) 1);
                ruleBreakRepository.updateRuleBreakTable(ruleBreakTableEntity.get(i).getAdminUId(),
                        ruleBreakTableEntity.get(i).getBreakUId(), ruleBreakTableEntity.get(i).getDecribe(),
                        ruleBreakTableEntity.get(i).getFlag(), ruleBreakTableEntity.get(i).getBreakLogId());
                ruleBreakRepository.flush();
            }
        }
    }

    @RequestMapping(value = "/json/changeInfo",method = RequestMethod.POST)
    @ResponseBody
    public void changeInfo(@RequestBody UserTableEntity userTableEntity,HttpSession session) {
        UserTableEntity userTableEntity1 = userRepository.findOrderByUId(Integer.valueOf((String)session.getAttribute("userCode"))).get(0);
        if (userTableEntity.equals(userTableEntity1)) {
            userRepository.updateUserInfo(userTableEntity.getuName(),userTableEntity.getuAge(),
                    userTableEntity.getuSex(),userTableEntity.getPhoneNumber(),
                    userTableEntity.getUserName(),userTableEntity.getPassword(),
                    userTableEntity.getPower(),userTableEntity1.getuId());
            userRepository.flush();
        }
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
