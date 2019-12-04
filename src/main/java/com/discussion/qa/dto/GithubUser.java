package com.discussion.qa.dto;

import lombok.Data;

/**
 * @author by SuiDongyang
 * @date 2019/10/31 10:38
 *
 * 从Github获取到的用户信息
 */
@Data
public class GithubUser {

    private String name;
    private Long id;
    private String bio;
    private String avatar_url;

}
