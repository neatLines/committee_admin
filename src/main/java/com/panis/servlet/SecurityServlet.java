package com.panis.servlet;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by fuyipeng on 2016/11/8.
 */
public class SecurityServlet implements HandlerInterceptor {

    private static final String LOGIN_URL = "login";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        HttpSession session = req.getSession(true);
        // 从session 里面获取用户名的信息
        String obj = (String)session.getAttribute("userCode");
        System.out.println(obj);
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
        if (obj == null || "".equals(obj)) {
            res.sendRedirect(LOGIN_URL);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3) throws Exception {
    }

}