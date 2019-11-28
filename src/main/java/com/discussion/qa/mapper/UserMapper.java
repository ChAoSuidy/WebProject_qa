package com.discussion.qa.mapper;

import com.discussion.qa.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author by SuiDongyang
 * @date 2019/11/27 09:26
 */
@Mapper
public interface UserMapper {

    @Insert("insert into tab_user (name, account_id, token, gmt_create, gmt_modified) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);

    @Select("select * from tab_user where token = #{token}")
    User findByToken(String token);
}
