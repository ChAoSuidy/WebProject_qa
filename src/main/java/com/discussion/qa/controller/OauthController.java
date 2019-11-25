package com.discussion.qa.controller;

import com.discussion.qa.dto.AccessTokenDTO;
import com.discussion.qa.dto.GithubUser;
import com.discussion.qa.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Oauth Apps
 * 获取从Github返回的code及state
 * 以便完成下一步的操作（使用code去Github获取token）
 */
@Controller
public class OauthController {

    @Autowired
    private GithubProvider githubProvider;

    //@ConfigurationProperties("github.client.id")
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                            HttpServletRequest request) {

        //使用Github发来的code完成accessToken的属性设置
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);

        //向Github发送获取accessToken的请求
        String token = githubProvider.getAccessToken(accessTokenDTO);
        //System.out.println(token);
        GithubUser user = githubProvider.getUser(token);
        //System.out.println(user.getName());
        if(user != null){
            //登录成功，写cookie和session
            request.getSession().setAttribute("user", user);
            //重定向是将网址重新加载
            return "redirect:/";

        }else{
            //登录失败，重新登录
            return "redirect:/";
        }

    }

}
