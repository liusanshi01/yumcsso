package com.para4digm.yumcdpl.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.para4digm.yumcdpl.dto.DataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommonUtils {
    /**
     * 判断输入的数据是否是正整数
     *
     * @param value
     * @param value
     */
    public static int checkPageInfo(String value) {
        int ret = -1;

        // 空
        if (StringUtils.isEmpty(value)) {
            ret = 0;
            // 数字
        } else if (CommonUtils.isNumeric(value)) {
            ret = Integer.parseInt(value);
            // 非数字
        } else if (!CommonUtils.isNumeric(value)) {
            ret = -1;
        }
        return ret;
    }

    /**
     * 数字check
     *
     * @param s
     * @return
     */
    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

    public static Map getMap(String path){
        Map map = new LinkedHashMap();
        try {
            //路径
            ClassPathResource classPathResource = new ClassPathResource(path);
            //读取文件信息
            String str = IOUtils.toString(new InputStreamReader(classPathResource.getInputStream(),"UTF-8"));
//            System.out.println(str);
            //转换为Map对象
            map = JSONObject.parseObject(str, LinkedHashMap.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main (String[] args){
//
//        Map<String, Object> result = CommonUtils.getMap("/json/dataFile.json");
//        JSONObject object = (JSONObject) result.get("dataSource");
//        DataSource dataSource = JSON.toJavaObject(object, DataSource.class);
//        dataSource.setUrl("123");
//
//        //这一步出错的
//        JSONObject json=JSONObject.parseObject(JSONObject.toJSON(dataSource).toString());
//        System.out.println(json);
//        System.out.println(dataSource);


    }



}
