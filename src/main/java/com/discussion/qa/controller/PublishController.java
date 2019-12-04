package com.discussion.qa.controller;

import com.discussion.qa.mapper.EpiphanyMapper;
import com.discussion.qa.mapper.UserMapper;
import com.discussion.qa.model.Epiphany;
import com.discussion.qa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Get 渲染页面
 * Post 处理请求
 *
 * @author by SuiDongyang
 * @date 2019/11/29 23:07
 */
@Controller
public class PublishController {

    @Autowired
    private EpiphanyMapper epiphanyMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tags") String tags,
            HttpServletRequest request,
            Model model
    ) {

        /**
         * 回显功能
         */
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tags", tags);

        /**
         * 服务端判断页面信息为空的问题
         */
        if (title == null || title == "") {
            model.addAttribute("error", "精华不能为空！");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "经历过程不能为空！");
            return "publish";
        }
        if (tags == null || tags == "") {
            model.addAttribute("error", "标签不能为空！");
            return "publish";
        }


        User user = null;
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
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Epiphany epiphany = new Epiphany();
        epiphany.setTitle(title);
        epiphany.setDescription(description);
        epiphany.setTags(tags);
        epiphany.setGmtCreate(System.currentTimeMillis());
        epiphany.setGmtModified(epiphany.getGmtCreate());
        epiphany.setCreator(user.getId());

        epiphanyMapper.shareEpiphany(epiphany);
        return "redirect:/";
    }
}
