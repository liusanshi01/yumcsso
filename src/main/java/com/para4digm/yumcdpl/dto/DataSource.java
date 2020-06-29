package com.para4digm.yumcdpl.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class DataSource {
    private String columnSpliter;

    private String dateFormat;

    private String dateTimeFormat;

    private String encoding;

    private List<String> fileSuffix;

    private String fileType;
    @JSONField(name = "isFirstLineSchema")
    private boolean isFirstLineSchema;

    private String lineSpliter;

    private String module;

    private String nullValues;

    private String originFileName;

    private String originFileType;

    private String originSourceProtocol;

    private String protocol;

    private String sql;

    private String sqlTableName;

    private String url;
}
