package com.panis.controller;

import com.panis.Dao.*;
import com.panis.DaoImpl.*;
import com.panis.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fuyipeng on 11/12/2016.
 */
@Controller
@SessionAttributes({"userCode","role"})
public class BaseAdminController {
    HouseDao houseDao;
    ParkDao parkDao;
    PersonnelDao personnelDao;
    PublicityDao publicityDao;
    RuleBreakDao ruleBreakDao;
    UserDao userDao;
    CommonalityDao commonalityDao;
    PropertyDao propertyDao;
    PropertyLogDao propertyLogDao;
    public BaseAdminController() {
        houseDao = new HouseDaoImpl();
        parkDao = new ParkDaoImpl();
        personnelDao = new PersonnelDaoImpl();
        publicityDao = new PublicityDaoImpl();
        ruleBreakDao = new RuleBreakDaoImpl();
        userDao = new UserDaoImpl();
        commonalityDao = new CommonalityDaoImpl();
        propertyDao = new PropertyDaoImpl();
        propertyLogDao = new PropertyLogDaoImpl();
    }

    public int isAdmin(HttpSession session) {
        String temp = null;
        try {
            temp = (String) session.getAttribute("role");
            System.out.println(temp);
        } catch (Exception e) {
            return -1;
        }
        if (!"1".equals(temp)) {
            return -1;
        } else {
            return 1;
        }
    }

    @RequestMapping(value = "/json/addNewPublicity",  method = RequestMethod.POST)
    @ResponseBody
    public Object addNewPublicity(@RequestBody PublicityTableEntity publicityTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                publicityTableEntity.setWriterId(Integer.valueOf((String) session.getAttribute("userCode")));
                publicityDao.insert(publicityTableEntity);
                return "{\"info\":\"insert success\"}";
            } catch (Exception e) {
                return "{\"info\":\"insert fail\"}";
            }
        }
    }

    @RequestMapping(value = "/json/changePublicity", method = RequestMethod.POST)
    @ResponseBody
    public Object changePublicity(@RequestBody PublicityTableEntity publicityTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                List list = new ArrayList();
                list.add(publicityTableEntity);
                publicityDao.updateById(list);
                return "{\"info\":\"update success\"}";
            } catch (Exception e) {
                return "{\"info\":\"update fail\"}";
            }
        }
    }

    @RequestMapping(value = "/json/deletePublicity", method = RequestMethod.POST)
    @ResponseBody
    public Object deletePublicity(@RequestBody PublicityTableEntity publicityTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role== -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            List list = new ArrayList();
            list.add(publicityTableEntity);
            try {
                if (publicityDao.delete(list))  {
                    return "{\"info\":\"delete success\"}";
                } else {
                    return "{\"info\":\"delete fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"what is happened\"}";
            }
        }
    }

    @RequestMapping(value = "/json/getAllCommonality", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllComminality(HttpSession session) {
        int role = isAdmin(session);
        if (false) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                return commonalityDao.findAll(CommonalityTableEntity.class);
            } catch (Exception e) {
                return "{\"info\":\"what is happened\"}";
            }
        }
    }

    @RequestMapping(value = "/json/changeCommonality", method = RequestMethod.POST)
    @ResponseBody
    public Object changeCommonality(@RequestBody CommonalityTableEntity commonalityTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            List list = new ArrayList();
            list.add(commonalityTableEntity);
            try {
                if (commonalityDao.updateById(list)) {
                    return "{\"info\":\"update success\"}";
                } else {
                    return "{\"info\":\"update fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"what is happened\"}";
            }
        }
    }

    @RequestMapping(value = "/json/addCommonality", method = RequestMethod.POST)
    @ResponseBody
    public Object addCommonality(@RequestBody CommonalityTableEntity commonalityTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if ((role) == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                if (commonalityDao.insert(commonalityTableEntity)) {
                    return "{\"info\":\"insert success\"}";
                } else {
                    return "{\"info\":\"insert fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"what happened\"}";
            }
        }
    }

    @RequestMapping(value = "/json/deleteCommonality", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteCommonality(@RequestBody CommonalityTableEntity commonalityTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                List list = new ArrayList();
                list.add(commonalityTableEntity);
                if (commonalityDao.delete(list)) {
                    return "{\"info\":\"delete success\"}";
                } else {
                    return "{\"info\":\"delete fail\"}";

                }
            } catch (Exception e) {
                return "{\"info\":\"what happened\"}";
            }
        }
    }

    @RequestMapping(value = "/json/addBreakLog", method = RequestMethod.POST)
    @ResponseBody
    public Object addBreakLog(@RequestBody Map map, HttpSession session) {
        RuleBreakTableEntity ruleBreakTableEntity = new RuleBreakTableEntity();

        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            ruleBreakTableEntity.setFlag(false);
            ruleBreakTableEntity.setDecribe((String) map.get("decribe"));
            try {
                ruleBreakTableEntity.setAdminUId(Integer.valueOf((String) session.getAttribute("userCode")));
                ruleBreakTableEntity.setBreakUId(userDao.findOrderByUserName((String) map.get("breakUserName")).get(0).getuId());
            } catch (Exception e) {
                return "{\"info\":\"userName not found\"}";
            }
            try {
                ruleBreakDao.insert(ruleBreakTableEntity);
                return "{\"info\":\"insert success\"}";

            } catch (Exception e) {
                return "{\"info\":\"insert fail\"}";

            }

        }
    }

    @RequestMapping(value = "/json/deleteBreakLog", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteBreakLog(@RequestBody RuleBreakTableEntity ruleBreakTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            List list = new ArrayList();
            list.add(ruleBreakTableEntity);
            try {
                if (ruleBreakDao.delete(list)) {
                    return "{\"info\":\"delete success\"}";
                } else {
                    return "{\"info\":\"delete fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"what happen\"}";
            }
        }
    }

    @RequestMapping(value = "/json/getAllProperty", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllProperty(HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                return propertyDao.findAll(PropertyTableEntity.class);
            } catch (Exception e) {
                return "{\"info\":\"what happen\"}";
            }
        }
    }

    @RequestMapping(value = "/json/addProperty", method = RequestMethod.POST)
    @ResponseBody
    public Object addProperty(@RequestBody PropertyTableEntity propertyTableEntity, HttpSession session) {

        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                if (propertyDao.insert(propertyTableEntity)) {
                    PropertyLogTableEntity propertyLogTableEntity = new PropertyLogTableEntity();
                    propertyLogTableEntity.setChangeUId(Integer.valueOf((String) session.getAttribute("userCode")));
                    propertyLogTableEntity.setChangedPrId(propertyTableEntity.getPropertyId());
                    propertyLogTableEntity.setChangeWay("增加");
                    propertyLogDao.insert(propertyLogTableEntity);
                    return "{\"info\":\"insert success\"}";
                } else {
                    return "{\"info\":\"insert fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"what happen\"}";
            }
        }
    }

    @RequestMapping(value = "/json/changeProperty",method = RequestMethod.POST)
    @ResponseBody
    public Object changeProperty(@RequestBody PropertyTableEntity propertyTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                List list = new ArrayList();
                list.add(propertyTableEntity);
                if (propertyDao.updateById(list)) {
                    PropertyLogTableEntity propertyLogTableEntity = new PropertyLogTableEntity();
                    propertyLogTableEntity.setChangeUId(Integer.valueOf((String) session.getAttribute("userCode")));
                    propertyLogTableEntity.setChangedPrId(propertyTableEntity.getPropertyId());
                    propertyLogTableEntity.setChangeWay("修改");
                    propertyLogDao.insert(propertyLogTableEntity);
                    return "{\"info\":\"change success\"}";
                } else {
                    return "{\"info\":\"change fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"what happen\"}";
            }
        }
    }

    @RequestMapping(value = "/json/deleteProperty",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteProperty(@RequestBody PropertyTableEntity propertyTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                List list = new ArrayList();
                list.add(propertyTableEntity);
                if (propertyDao.delete(list)) {
                    PropertyLogTableEntity propertyLogTableEntity = new PropertyLogTableEntity();
                    propertyLogTableEntity.setChangeUId(Integer.valueOf((String) session.getAttribute("userCode")));
                    propertyLogTableEntity.setChangedPrId(propertyTableEntity.getPropertyId());
                    propertyLogTableEntity.setChangeWay("删除");
                    propertyLogDao.insert(propertyLogTableEntity);
                    return "{\"info\":\"delete success\"}";
                } else {
                    return "{\"info\":\"delete fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"what happen\"}";
            }
        }
    }

    @RequestMapping(value = "/json/getAllPropertyLog", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllPropertyLog(HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                return propertyLogDao.findAll(PropertyLogTableEntity.class);
            } catch (Exception e) {
                return "{\"info\":\"what happen\"}";
            }
        }
    }

}
