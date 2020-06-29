//package com.para4digm.yumcdpl.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Description 接口返回结果中code与message映射表
// * @Author lancy
// * @Date 2020-04-27 15:02
// */
//public class ResponseUtils {
//    private static Map<Integer, String> codeMessageMap = new HashMap<Integer, String>();
//
//    static {
//        // 成功
//        codeMessageMap.put(BusDict.Success, "operation success!");
//        // 各种失败
//        codeMessageMap.put(BusDict.UnKnow_error, "unknown error！");
//    }
//
//    public static CommonResult renderResult(CommonResult result, Integer code) {
//        result.setCode(code);
//        result.setMsg(codeMessageMap.get(code));
//        return result;
//    }
//
//    public static CommonResult renderResult(CommonResult result, Integer code, String message) {
//        result.setCode(code);
//        result.setMsg(message);
//        return result;
//    }
//}
