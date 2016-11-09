package com.panis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by fuyipeng on 2016/11/8.
 */
@Controller
@SessionAttributes({"userCode","role"})
public class MainController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value="/check", method=RequestMethod.GET)
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

        return "/test";
    }
}
