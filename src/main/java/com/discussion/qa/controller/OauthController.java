package com.discussion.qa.controller;

import com.discussion.qa.dto.AccessTokenDTO;
import com.discussion.qa.dto.GithubUser;
import com.discussion.qa.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Oauth Apps
 * 获取从Github返回的code及state
 * 以便完成下一步的操作（使用code去Github获取token）
 */
@Controller
public class OauthController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {

        //使用Github发来的code完成accessToken的属性设置
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("3cd939de43df96db3f3e");
        accessTokenDTO.setClient_secret("e7329bafee625cee8a95bb38d62e74a6cc7a0848");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8886/callback");
        accessTokenDTO.setState(state);

        //向Github发送获取accessToken的请求
        String token = githubProvider.getAccessToken(accessTokenDTO);
        //System.out.println(token);
        GithubUser user = githubProvider.getUser(token);
        System.out.println(user.getName());

        return "index";
    }

}
