package com.discussion.qa.controller;

import com.discussion.qa.dto.AccessTokenDTO;
import com.discussion.qa.dto.GithubUser;
import com.discussion.qa.mapper.UserMapper;
import com.discussion.qa.model.User;
import com.discussion.qa.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 这个错误是因为Mybatis的启动器并不是Spring官方出的，所以Spring会认为UserMapper对象没有被注入容器。
     * 实际上已经UserMapper已经通过@Mapper注册，并且通过@Autowired注解注入容器了。
     * 大家可以在报错的地方alt+enter，在第一个选项inspection里选择忽略掉这个错误，实际上不影响应用运行的。
     */
//    @Autowired
//    private UserService userService;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        //使用Github发来的code完成accessToken的属性设置
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);

        //向Github发送获取accessToken的请求
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //System.out.println(token);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        //System.out.println(githubUser.getName());
        if (githubUser != null && githubUser.getId() != null) {
            //user is not null, put the user info into database
            User user = new User();
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setImageUrl(githubUser.getAvatar_url());
            userMapper.insert(user);
            //userService.addUser(user);

            response.addCookie(new Cookie("token", token));

            //登录成功，写cookie和session
            //request.getSession().setAttribute("user", githubUser);

            //重定向是将网址重新加载
            return "redirect:/";

        } else {
            //登录失败，重新登录
            return "redirect:/";
        }

    }

}
