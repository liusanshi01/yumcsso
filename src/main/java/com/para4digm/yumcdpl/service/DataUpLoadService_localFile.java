package com.para4digm.yumcdpl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.para4digm.yumcdpl.dto.DataSource;
import com.para4digm.yumcdpl.util.CommonUtils;
import com.para4digm.yumcdpl.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@Slf4j
@Service
public class DataUpLoadService_localFile {

    @Value("${sso.sage.permanentKey}")
    private String accessKey;
    @Value("${sso.sage.allocationSpaceToUserUrl}")
    private String allocationSpaceToUserUrl;
    @Value("${sso.sage.queryWorkSpaceListUrl}")
    private String queryWorkSpaceListUrl;
    @Value("${sso.sage.url}")
    private String url;
    @Value("${sso.sage.pageSize}")
    private int pageSize;
    @Value("${sso.sage.pageNo}")
    private int pageNo;
    @Value("${sso.sage.WorkSpaceId}")
    private int WorkSpaceId;

    /**
     * 取得工作区集合
     */
    public boolean dataUpload(MultipartFile file) {

        try {
            String uploadurl = url + "/telamon/v1/data/upload";
            //获取pdms的 hdfs
            String hdfsurl = HttpClientUtil.sendPost(uploadurl, file, WorkSpaceId,accessKey);

            //http://172.27.133.216/telamon/v1/data/file
            Map<String, Object> result = CommonUtils.getMap("/json/dataFile.json");
            JSONObject object = (JSONObject) result.get("dataSource");
            DataSource dataSource = JSON.toJavaObject(object, DataSource.class);
            dataSource.setUrl(hdfsurl);

            JSONObject json=JSONObject.parseObject(JSONObject.toJSON(dataSource).toString());
            result.put("dataSource", json);
            Object json2 =  JSONObject.toJSON(result);
            String datafileurl = url + "/telamon/v1/data/file";
            String result2 = HttpClientUtil.sendPostDataFile(datafileurl, json2.toString(), WorkSpaceId,accessKey);
            System.out.println(result2);
            Map mapresult = JSON.parseObject(result2,Map.class);

            //如果status为0就返回true,非0返回false.
            String status = (String)mapresult.get("status");
            if (status.equals("0")){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


}
