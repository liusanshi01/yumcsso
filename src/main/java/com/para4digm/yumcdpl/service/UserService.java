package com.para4digm.yumcdpl.service;


import com.para4digm.yumcdpl.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUser();
    void insertUser(UserDto userDto);
}
