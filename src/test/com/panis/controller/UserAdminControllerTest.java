package com.panis.controller;

import com.panis.Dao.RuleBreakDao;
import com.panis.DaoImpl.*;
import com.panis.model.RuleBreakTableEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by fuyipeng on 01/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class UserAdminControllerTest {
    RuleBreakDao ruleBreakDao;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAllBreakLog() throws Exception {
        ruleBreakDao = new RuleBreakDaoImpl();
        List<RuleBreakTableEntity> ruleBreakTableEntities = null;
        try {
            ruleBreakTableEntities = ruleBreakDao.findAll(RuleBreakTableEntity.class);
        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        for (RuleBreakTableEntity ruleBreakTableEntity:ruleBreakTableEntities) {
            System.out.println(ruleBreakTableEntity.toString());
        }
    }

    @Test
    public void getBreakLog() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userCode","1");

        System.out.println("inside check");

        System.out.println("");
        System.out.println("*** Session data ***");
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String s = e.nextElement();
            System.out.println(s + " == " + session.getAttribute(s));
        }
        ruleBreakDao = new RuleBreakDaoImpl();
        List<RuleBreakTableEntity> ruleBreakTableEntities = ruleBreakDao.findOrderByBreakUId(Integer.parseInt((String)session.getAttribute("userCode")));

        System.out.println(ruleBreakTableEntities.get(0).toString());

    }

    @Test
    public void setHadWrite() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userCode","1");
        ruleBreakDao = new RuleBreakDaoImpl();
        List<RuleBreakTableEntity> ruleBreakTableEntity = ruleBreakDao.findOrderByBreakUId(Integer.parseInt((String)session.getAttribute("userCode")));
        int count = 0;
        try {
            for (int i = 0; i < ruleBreakTableEntity.size(); i++) {
                if (ruleBreakTableEntity.get(i).getBreakUId() == Integer.parseInt((String) session.getAttribute("userCode"))) {
                    ruleBreakTableEntity.get(i).setFlag(true);
                    ruleBreakDao.updateRuleBreakTable(ruleBreakTableEntity.get(i).getAdminUId(),
                            ruleBreakTableEntity.get(i).getBreakUId(), ruleBreakTableEntity.get(i).getDecribe(),
                            ruleBreakTableEntity.get(i).isFlag(), ruleBreakTableEntity.get(i).getBreakLogId());
                    ruleBreakDao.flush();
                    count++;
                    System.out.println(ruleBreakTableEntity.get(0).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void changeInfo() throws Exception {

    }

    @Test
    public void changeHouseInfo() throws Exception {

    }

    @Test
    public void getAllPublic() throws Exception {

    }

    @Test
    public void getDetail() throws Exception {

    }

    @Test
    public void getPersonnelOnlyRead() throws Exception {

    }

    @Test
    public void getOwnPark() throws Exception {

    }

}