package com.discussion.qa.service;

import com.discussion.qa.mapper.UserMapper;
import com.discussion.qa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by SuiDongyang
 * @date 2019/11/27 20:57
 */
@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.insert(user);
    }
}
