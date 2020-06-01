package com.para4digm.yumcdpl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.para4digm.yumcdpl.dto.DataSource;
import com.para4digm.yumcdpl.dto.DataTarget;
import com.para4digm.yumcdpl.util.CommonUtils;
import com.para4digm.yumcdpl.util.DataUtils;
import com.para4digm.yumcdpl.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
public class dataUpLoadService {

    @Value("${sso.sage.permanentKey}")
    private String accessKey;
    @Value("${sso.sage.url}")
    private String url;
    @Value("${sso.sage.pageSize}")
    private int pageSize;
    @Value("${sso.sage.pageNo}")
    private int pageNo;
    @Value("${sso.sage.WorkSpaceId}")
    private int WorkSpaceId;

    /**
     * 数据预览
    **/
    private Object previewdate(String body){

        String previewurl = url+"/telamon/v1/data/file/preview?limit=10";
        String previewresult = HttpClientUtil.sendPostDataFile(previewurl,body, WorkSpaceId,accessKey);
        Map<String,Object> previewmap = JSON.parseObject(previewresult,Map.class);
        Object data = previewmap.get("data");
        Map<String,Object> schemamap = JSON.parseObject(data.toString(),Map.class);

        return schemamap.get("schema");
    }
    /**
     * 数据上传
     **/
    public boolean dataUpload(String Hdfsurl) {

        //http://172.27.133.216/telamon/v1/data/file
        Map<String, Object> result = CommonUtils.getMap("/json/dataFile.json");
        JSONObject dataSourceobject = (JSONObject) result.get("dataSource");
        DataSource dataSource = JSON.toJavaObject(dataSourceobject, DataSource.class);
        dataSource.setUrl(Hdfsurl);
        JSONObject DSjson = JSONObject.parseObject(JSONObject.toJSON(dataSource).toString());

        result.put("dataSource", DSjson);

        //dataTarget//previewdate
        JSONObject dataTargetobject = (JSONObject) result.get("dataTarget");
        DataTarget datatarget = JSON.toJavaObject(dataTargetobject, DataTarget.class);
//
        Object scheama = previewdate(DSjson.toString());
        datatarget.setSchema(scheama);
        //每次prn的设置
        datatarget.setPrn("kipmid/project" + DataUtils.getprnString()+".table");
        JSONObject DTjson = JSONObject.parseObject(JSONObject.toJSON(datatarget).toString());
        result.put("dataTarget",DTjson);

        Object json2 =  JSONObject.toJSON(result);
        //preview data

        String datafileurl = url + "/telamon/v1/data/file";
        String result2 = HttpClientUtil.sendPostDataFile(datafileurl, json2.toString(), WorkSpaceId,accessKey);
        System.out.println(result2);
        Map mapresult = JSON.parseObject(result2,Map.class);

        //如果status为0就返回true,非0返回false.
        String status = (String)mapresult.get("status");
        if (status.equals("0")){
            return true;
        }
        return false;
    }


}
