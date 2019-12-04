package com.discussion.qa.dto;

import lombok.Data;

/**
 * 数据访问对象
 * 用于从客户端向Github发送数据
 */
@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
