package com.panis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by fuyipeng on 28/11/2016.
 */
@Controller
@SessionAttributes({"userCode","role"})
public class UserAdminController {
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
