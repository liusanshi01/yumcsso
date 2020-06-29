package com.para4digm.yumcdpl.service.impl;


import com.para4digm.yumcdpl.dto.UserDto;
import com.para4digm.yumcdpl.mapper.UserMapper;
import com.para4digm.yumcdpl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;



    @Override
    public List<UserDto>  getAllUser() {
        List<UserDto> userlist = userMapper.getAllUser();
        return userlist;
//        return userMapper.checkUser(username,password);
    }

    @Override
    public void  insertUser(UserDto userDto) {
        userMapper.insert(userDto);

    }

}
