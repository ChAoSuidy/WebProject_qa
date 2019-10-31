package com.discussion.qa.provider;

import com.alibaba.fastjson.JSON;
import com.discussion.qa.dto.AccessTokenDTO;
import com.discussion.qa.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 第三方应用提供者
 * 此处为Github
 */

@Component
public class GithubProvider {

    //用code向Github获取token
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            String token = str.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //用token向Github获取user信息
    public GithubUser getUser(String accessToken) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            GithubUser user = JSON.parseObject(str, GithubUser.class);
            return user;
        } catch (IOException e) {
        }
        return null;
    }
}
