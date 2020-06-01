package com.para4digm.yumcdpl.controller;


import com.alibaba.fastjson.JSONObject;
import com.para4digm.yumcdpl.client.ProphetClient;
import com.para4digm.yumcdpl.dto.UserDto;
import com.para4digm.yumcdpl.service.LoginService;
import com.para4digm.yumcdpl.service.dataUpLoadService;
import com.para4digm.yumcdpl.service.platformService;
import com.para4digm.yumcdpl.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;


@Slf4j
@Controller
public class cdplContioller {

    @Autowired
    private LoginService loginService;

    @Autowired
    private platformService platformservice;

    @Autowired
    private dataUpLoadService datauploadservice;
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    ProphetClient prophetClient;

    //上传文件的接口
    @RequestMapping(value = "/domodel" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ModelAndView domodel(HttpServletRequest request, Model model ) throws IOException {

        ModelAndView mav = new ModelAndView();
        String hdfsurl = request.getParameter("hdfsurl");
        boolean result = datauploadservice.dataUpload(hdfsurl);
        System.out.println(result);

        mav.setViewName("success");
        return mav;

    }


    //上传文件的接口
    @RequestMapping(value = "/upload")
    @ResponseBody
    public ModelAndView upload(@RequestParam("file1") MultipartFile file) throws IOException {
        ModelAndView mav = new ModelAndView();
//        boolean result = datauploadservice_localFile.dataUpload(file);
        if(true){
            mav.setViewName("success");
            return mav;
        }
        else {
            mav.setViewName("hello");
            return mav;
        }
    }


    //测试返回对应用户所有的workspace
    @RequestMapping("/getWP")
    public String hello(Model model) {
        platformservice.getWorkSpaceList();
        return "success.html";
    }
    //测试能否访问flowegine接口
    @RequestMapping("/getFl")
    public String getflowegine(Model model) {
        platformservice.getflowegineList();
        return "success.html";
    }

    @RequestMapping("/login")
    private String index() {
        return "login.html";
    }

    @RequestMapping("/dodataup")
    private String dodataupindex() {
        return "dodataup.html";
    }

    @RequestMapping("/fileupdatatest")
    private String helloindex() {
        return "hello.html";
    }
    /*
     **输入账号密码登录校验方法
     * @param message
     * @author lgf
     * */
    @RequestMapping(value = "/loginPage", method = {RequestMethod.GET, RequestMethod.POST})
    private ModelAndView login(HttpServletRequest request, Model model) throws Exception {
        ModelAndView mav = new ModelAndView();

        UserDto user = new UserDto();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("pwd"));
        Boolean res = loginService.auth(JSONObject.parseObject(JSONObject.toJSON(user).toString()));

        if (!res) {
            String name2 = "账号或者密码错误";
            model.addAttribute("name",name2);
            mav.setViewName("login");
            return mav;
        } else {
            //验证通过跳转首页
            mav.setViewName("success");
            return mav;
        }
    }


}
