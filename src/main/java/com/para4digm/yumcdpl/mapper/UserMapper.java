package com.para4digm.yumcdpl.mapper;

import com.para4digm.yumcdpl.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

         //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface UserMapper {

    List<UserDto> getAllUser();
    void insert(UserDto userDto);

}