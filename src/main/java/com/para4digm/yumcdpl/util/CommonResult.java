//package com.para4digm.yumcdpl.util;
//
//import com.alibaba.fastjson.JSON;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
//import java.io.Serializable;
//
//@ApiModel("统一结果返回格式")
//public class CommonResult<T> implements Serializable {
//    @ApiModelProperty("状态码")
//    private Integer code;
//    @ApiModelProperty("信息描述")
//    private String msg;
//    @ApiModelProperty("结果数据")
//    private T data;
//
//    public CommonResult() {
//    }
//
//    ;
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//    @Override
//    public String toString() {
//        return JSON.toJSONString(this);
//    }
//}
//
