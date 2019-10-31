package com.discussion.qa.dto;

/**
 * @author by SuiDongyang
 * @date 2019/10/31 10:38
 *
 * 从Github获取到的用户信息
 */
public class GithubUser {

    private String name;
    private Long id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
