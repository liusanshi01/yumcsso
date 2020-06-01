//package com.para4digm.yumcdpl.mapper;
//
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//import org.springframework.stereotype.Repository;
//
//@Mapper         //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
//@Repository
//public interface UserMapper {
//    @Select("select username from userdto where username=#{username} and password=#{password}")
//    String checkUser(@Param("username") String username , @Param("password") String password);
////    @Select("select name,id from user where name=#{name}")
////    UserDto getAllUserByName(@Param("username") String name);
////    UserDto loginPage(String name,String id);
//}