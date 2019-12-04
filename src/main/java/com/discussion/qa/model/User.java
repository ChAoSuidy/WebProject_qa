package com.discussion.qa.model;

import lombok.Data;

/**
 * @author by SuiDongyang
 * @date 2019/11/27 09:55
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String imageUrl;

}
