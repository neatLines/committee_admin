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

// TODO: 12/12/2016 有时间重构
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

    /**
     * json接口
     * 插入一条新的公告
     * 传入一个PublicityTableEntity
     * 验证登陆情况
     * 没有权限返回permission denied
     * 插入成功返回success
     * 插入失败返回fail
     * @param publicityTableEntity
     * @param session
     * @return
     */
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

    /**
     * json接口
     * 修改公告
     * 传入一个PublicityTableEntity
     * 验证登陆情况
     * 没有权限返回permission denied
     * 修改成功返回success
     * 修改失败返回fail
     * @param publicityTableEntity
     * @param session
     * @return
     */
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

    /**
     * json接口
     * 删除一条公告
     * 传入一个PublicityTableEntity
     * 验证登陆情况
     * 没有权限返回permission denied
     * 删除成功返回success
     * 删除失败返回fail
     * @param publicityTableEntity
     * @param session
     * @return
     */
    @RequestMapping(value = "/json/deletePublicity", method = RequestMethod.POST)
    @ResponseBody
    public Object deletePublicity(@RequestBody PublicityTableEntity publicityTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
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

    /**
     * json接口
     * 得到所有公共设施
     * 验证登陆情况
     * 没有权限返回permission denied
     * 成功返回所有公共设施
     * 失败返回json提示
     * @param session
     * @return
     */
    @RequestMapping(value = "/json/getAllCommonality", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllComminality(HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                return commonalityDao.findAll(CommonalityTableEntity.class);
            } catch (Exception e) {
                return "{\"info\":\"what is happened\"}";
            }
        }
    }

    /**
     * json接口
     * 改变公共设施信息
     * 传入一个CommonalityTableEntity
     * 验证登陆情况
     * 没有权限返回permission denied
     * 成功返回success
     * 失败返回fail
     * @param commonalityTableEntity
     * @param session
     * @return
     */
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

    /**
     * json接口
     * 增加公共设施信息
     * 传入一个CommonalityTableEntity
     * 验证登陆情况
     * 没有权限返回permission denied
     * 成功返回success
     * 失败返回fail
     * @param commonalityTableEntity
     * @param session
     * @return
     */
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

    /**
     * json接口
     * 删除公共设施信息
     * 传入一个CommonalityTableEntity
     * 验证登陆情况
     * 没有权限返回permission denied
     * 成功返回success
     * 失败返回fail
     * @param commonalityTableEntity
     * @param session
     * @return
     */
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

    /**
     * json接口
     * 增加违章记录
     * 传入一个ruleBreakTableEntity————不需要adminUid
     * 验证登陆情况
     * 没有权限返回permission denied
     * 成功返回success
     * 失败返回fail
     * @param map
     * @param session
     * @return
     */
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
                ruleBreakTableEntity.setBreakUId((Integer) map.get("breakUId"));
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

    /**
     * json接口
     * 删除违章记录
     * 传入一个ruleBreakTableEntity
     * 验证登陆情况
     * 没有权限返回permission denied
     * 成功返回success
     * 失败返回fail
     * @param ruleBreakTableEntity
     * @param session
     * @return
     */
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

    /**
     * json接口
     * 得到所有的财产信息
     * 验证登陆情况
     * 没有权限返回permission denied
     * 成功返回propertyTableEntity
     * 失败返回fail
     * @param session
     * @return
     */
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

    /**
     * json接口
     * 增加一种资产
     * 验证登陆情况
     * 没有权限则返回permission denied
     * 传入一个propertyTableEntity
     * 成功返回success
     * 失败返回fail
     * 同步添加log
     * @param propertyTableEntity
     * @param session
     * @return
     */
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

    /**
     * json接口
     * 改变一种资产
     * 验证登陆情况
     * 没有权限则返回permission denied
     * 传入propertyTableEntity
     * 成功返回success
     * 失败返回fail
     * 同步添加log
     * @param propertyTableEntity
     * @param session
     * @return
     */
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

    /**
     * json接口
     * 删除一种资产
     * 验证登陆情况
     * 没有权限则返回permission denied
     * 传入propertyTableEntity
     * 成功返回success
     * 失败返回fail
     * 同步添加log
     * @param propertyTableEntity
     * @param session
     * @return
     */
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


    /**
     * json接口
     * 得到所有资产操作记录
     * 验证登陆情况
     * 没有权限则返回permission denied
     * 成功返回所有记录
     * 失败返回fail
     * 同步添加log
     * @param session
     * @return
     */
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


    @RequestMapping(value = "/json/addUser", method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(@RequestBody UserTableEntity userTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                if (userDao.insert(userTableEntity)) {
                    return "{\"info\":\"insert success\"}";
                } else {
                    return "{\"info\":\"insert fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"something happened\"}";
            }
        }
    }


    @RequestMapping(value = "/json/changeUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object changeUserInfo(@RequestBody UserTableEntity userTableEntity,HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                UserTableEntity temp = (UserTableEntity) userDao.findById(userTableEntity).get(0);
                userTableEntity.setPower(temp.getPower());
                List list = new ArrayList();
                list.add(userTableEntity);
                if (userDao.updateById(list)) {
                    return "{\"info\":\"change success\"}";
                } else {
                    return "{\"info\":\"change fail\"}";

                }
            } catch (Exception e) {
                return "{\"info\":\"something happened\"}";
            }
        }
    }


    @RequestMapping(value = "/json/addPark", method = RequestMethod.POST)
    @ResponseBody
    public Object addPark(@RequestBody ParkTableEntity parkTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                if (parkDao.insert(parkTableEntity)) {
                    return "{\"info\":\"insert success\"}";
                } else {
                    return "{\"info\":\"insert fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"something happened\"}";
            }
        }
    }

    @RequestMapping(value = "/json/deletePark", method = RequestMethod.POST)
    @ResponseBody
    public Object deletePark(@RequestBody ParkTableEntity parkTableEntity, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try{
                List list = new ArrayList();
                list.add(parkTableEntity);
                if (parkDao.delete(list)) {
                    return "{\"info\":\"delete success\"}";
                } else {
                    return "{\"info\":\"delete fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"something happened\"}";
            }
        }
    }


    @RequestMapping(value = "/json/getAllPark", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllPark(HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                return parkDao.findAll(ParkTableEntity.class);
            } catch (Exception e) {
                return "{\"info\":\"something happened\"}";
            }
        }
    }

    @RequestMapping(value = "/json/getParkByPlace", method = RequestMethod.POST)
    @ResponseBody
    public Object getParkByPlace(@RequestBody Map map, HttpSession session) {
        int role = isAdmin(session);
        if (role == -1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                return parkDao.findOrderByLikePlace(map.get("place").toString());
            } catch (Exception e) {
                return "{\"info\":\"something happened\"}";
            }
        }
    }

    @RequestMapping(value = "/json/getAllHouseInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllHouseInfo(HttpSession session) {
        if (false) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                return houseDao.findAllLinkUserTable(HouseTableEntity.class);
            } catch (Exception e) {
                return "{\"info\":\"something happened\"}";
            }
        }
    }

    @RequestMapping(value = "/json/addHouse", method = RequestMethod.POST)
    @ResponseBody
    public Object addNewHouse(@RequestBody HouseTableEntity houseTableEntity, HttpSession session) {
        if (isAdmin(session)==-1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                if (houseDao.insert(houseTableEntity)) {
                    return "{\"info\":\"insert success\"}";
                } else {
                    return "{\"info\":\"insert fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"something happened\"}";
            }
        }
    }

    @RequestMapping(value = "/json/deleteHouse", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteHouse(@RequestBody HouseTableEntity houseTableEntity, HttpSession session) {
        if (isAdmin(session)==-1) {
            return "{\"info\":\"permission denied\"}";
        } else {
            try {
                List list = new ArrayList();
                list.add(houseTableEntity);
                if (houseDao.delete(list)) {
                    return "{\"info\":\"insert success\"}";
                } else {
                    return "{\"info\":\"insert fail\"}";
                }
            } catch (Exception e) {
                return "{\"info\":\"something happened\"}";
            }
        }
    }
}
