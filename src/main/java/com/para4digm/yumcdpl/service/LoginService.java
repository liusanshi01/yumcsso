package com.para4digm.yumcdpl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.para4digm.yumcdpl.client.ProphetClient;
import com.para4digm.yumcdpl.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import plugin.PluginInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class LoginService {

    @Value("${sso.sage.url}")
    private String url;
    @Value("${sso.plugin.name}")
    private String pluginName;
    @Autowired
    HttpServletResponse response;
    @Autowired
    HttpServletRequest request;
    @Autowired
    ProphetClient prophetClient;

    public boolean auth(JSONObject authInfo) throws IOException {
        PluginInterface plugin = null;
        try {
            Class pluginClass = Class.forName("plugin." + pluginName + ".Authentication");
            plugin = (PluginInterface) pluginClass.newInstance();
        } catch (ClassNotFoundException e) {
            log.error("验证失败：", e.getMessage(), e.getCause());
//            e.printStackTrace();
            return false;
        } catch (InstantiationException e) {
            log.error("验证失败：", e.getMessage(), e.getCause());
//            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            log.error("验证失败：", e.getMessage(), e.getCause());
//            e.printStackTrace();
            return false;
        }
//         各个商户独自验证
        String userName = plugin.auth(authInfo, request, response);
        if (userName == null || userName.isEmpty()) {
            log.info("认证系统认证失败：", authInfo.toJSONString());
            return false;
        }
//        先知认证,获取token、设置cookie
        prophetClient.grant(response, userName);
        String result = HttpClientUtil.sendPost(url+"/keystone/v1/sessions",authInfo.toJSONString());
        Map mapresult = JSON.parseObject(result,Map.class);

        //如果status为0就返回true,非0返回false.
        String status = (String)mapresult.get("status");
        if (status.equals("0")){
            return true;
        }
        return false;
    }
}
