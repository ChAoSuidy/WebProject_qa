package com.discussion.qa.controller;

import com.discussion.qa.mapper.UserMapper;
import com.discussion.qa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 默认主页
 *
 * 将token从session存入Cookie，绕过服务器，直接用cookie和数据库校验登录，避免了服务器重启时用户登录信息重置，重复登录的繁琐操作。
 */
@Controller
public class IndexController {


    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        /**
         * get the cookie from front-end by request
         */
        Cookie[] cookies = request.getCookies();

        /**
         * traversal（遍历）cookies find the var named token
         *
         * if token exist
         *
         * get token and break
         */
        if (cookies == null) {
            return "index";
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        return "index";
    }
}
