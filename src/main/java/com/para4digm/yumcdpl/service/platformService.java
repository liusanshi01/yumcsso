package com.para4digm.yumcdpl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.para4digm.yumcdpl.exception.InvalidException;
import com.para4digm.yumcdpl.dto.WorkSpaceInfo;
import com.para4digm.yumcdpl.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class platformService {

    @Value("${sso.sage.permanentKey}")
    private String accessKey;
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
    public Map getWorkSpaceList() {

        Map dataMap = new HashMap();
        try {
            String getwpurl = queryWorkSpaceListUrl + "size=" + pageSize + "&page=" + pageNo;
            String result = HttpClientUtil.doGet(getwpurl, accessKey);

            System.out.println(result);
            Map resultMap = JSON.parseObject(result, Map.class);
            List<WorkSpaceInfo> retList = new ArrayList<>();
            WorkSpaceInfo workSpaceInfo = new WorkSpaceInfo();
            if (null != resultMap && "0".equals(String.valueOf(resultMap.get("status")))) {
                dataMap = (Map) resultMap.get("data");
                List<Map> resultList = JSONObject.parseArray(((JSONArray) dataMap.get("list")).toJSONString(), Map.class);
                if (null != resultList && resultList.size() > 0) {
                    for (Map wspInfo : resultList) {
                        workSpaceInfo = new WorkSpaceInfo();
                        workSpaceInfo.setWorkSpaceName(String.valueOf(wspInfo.get("workspaceName")));
                        workSpaceInfo.setWorkSpaceId(String.valueOf(wspInfo.get("id")));
                        workSpaceInfo.setUserId(String.valueOf(wspInfo.get("creatorId")));
                        workSpaceInfo.setUserName(String.valueOf(wspInfo.get("creatorName")));
                        workSpaceInfo.setDescription(String.valueOf(wspInfo.get("description")));
                        retList.add(workSpaceInfo);
                    }
                }
                dataMap.put("list", retList);
            } else {
                log.error("工作空间检索失败：返回code：{},错误信息：{}", resultMap.get("status"), (String) resultMap.get("msg"));
                throw new InvalidException("工作空间检索失败：{}", (String) resultMap.get("msg"));
            }
        } catch (Exception e) {
            log.error("工作空间检索失败", e.getMessage(), e.getCause());
            throw new InvalidException("工作空间检索失败");
        }
        return dataMap;
    }

    /**
     * 根据workspaceId获取取得flowegine的情况
     */
    public void getflowegineList() {
        String WorkSpaceurl = url+"/automl-manager/v1/appList?workspaceId="+WorkSpaceId;
        String result = HttpClientUtil.doGet(WorkSpaceurl, accessKey);
        System.out.println(result);

    }

}
