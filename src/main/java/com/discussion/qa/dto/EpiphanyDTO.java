package com.discussion.qa.dto;

import com.discussion.qa.model.User;
import lombok.Data;

/**
 * @author by SuiDongyang
 * @date 2019/12/5 22:51
 *
 * 这个DTO的作用就是在不该变数据库表的情况下
 *
 * 将多个表的数据进行关联
 *
 * 这里具体是将tab_user中的头像地址image_url添加到tab_epiphany中
 *
 * 使得可以直接通过tab_epiphany查到的epiphany对象得到user的头像地址
 *
 */
@Data
public class EpiphanyDTO {

    private Integer id;
    private String title;
    private String tags;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likes;
    private User user;
}
