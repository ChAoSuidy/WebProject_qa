package com.discussion.qa.model;

import lombok.Data;

/**
 * @author by SuiDongyang
 * @date 2019/11/30 16:17
 */
@Data
public class Epiphany {

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

}
