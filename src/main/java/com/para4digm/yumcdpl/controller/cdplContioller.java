package com.para4digm.yumcdpl.controller;

import com.para4digm.yumcdpl.dto.UserDto;
import com.para4digm.yumcdpl.service.impl.UserServiceImpl;
import com.para4digm.yumcdpl.util.CalculateMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Slf4j
@Controller
public class cdplContioller {

    @Autowired
    private UserServiceImpl userService;



    //ModelAndView login(HttpServletRequest request, Model model）
    @GetMapping(value="/login")
    @ResponseBody
//    @GenericResponseBody
    private ModelAndView getallUser() {
        //这里面mysql账户成功验证。
        List<UserDto> userlist = userService.getAllUser();
//        return userlist;

        ModelAndView mav = new ModelAndView();
        //out.print("ajax进入后台！！");

        UserDto UserDto_all = new UserDto();
        Double my_money = 0.0;
        for (UserDto user:userlist) {
            UserDto_all.setName(user.getName());
            my_money = my_money + user.getMoney();
        }
        UserDto_all.setMoney(my_money);


        mav.addObject("UserALL",UserDto_all);
        mav.setViewName("login");
        return mav;



    }

    @RequestMapping(value = "/calculate",method =RequestMethod.POST)
//    @PostMapping
    private ModelAndView calcu(HttpServletRequest request) {


        ModelAndView model =new ModelAndView();
        UserDto userDto = new UserDto();
        userDto.setName("liulei");
        userDto.setInsertTime(new Date());

        String F4 = request.getParameter("f4");
        String result = request.getParameter("result");

        Double money_result = 0.0;
        //这里做选择框
        if(result.equals("+")){
            userDto.setMoney(CalculateMethod.calculate(F4));

        }else if(result.equals("-")){
            userDto.setMoney(CalculateMethod.negative(F4));
        }
        userService.insertUser(userDto);

        List<UserDto> userlist = userService.getAllUser();

        UserDto UserDto_all = new UserDto();
        Double my_money = 0.0;
        for (UserDto user:userlist) {
            UserDto_all.setName(user.getName());
            my_money = my_money + user.getMoney();
        }
        UserDto_all.setMoney(my_money);


        model.addObject("UserALL",UserDto_all);

        model.setViewName("login");
        return model;
    }
}
