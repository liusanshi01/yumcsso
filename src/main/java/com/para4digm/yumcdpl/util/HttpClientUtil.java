//package com.para4digm.yumcdpl.util;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.*;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.HttpMultipartMode;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
///**
// * HttpClient处理工具类
// */
//@Slf4j
//public class HttpClientUtil {
//    /**
//     *  带参数的post请求
//     *
//     * @param url
//     * @param file
//     * @return
//     */
//    public static String sendPost(String url, MultipartFile file,int WorkSpaceId,String Access_Key) throws IOException {
//
//        String responseContent = null;
//        try {
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setHeader("Access-Key", Access_Key);
//            httpPost.addHeader("X-Prophet-Workspace-Id", String.valueOf(WorkSpaceId));
//
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
//            String fileName = file.getOriginalFilename();
//            builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);
//            HttpEntity multipart = builder.build();
//            httpPost.setEntity(multipart);
//
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            responseContent = EntityUtils.toString(entity, StandardCharsets.UTF_8.toString());
//            Map<String,Object> jsonToMap = JSONObject.parseObject(responseContent);
//            String status = (String) jsonToMap.get("status");
//            String hdfsurl = (String)jsonToMap.get("data");
//            if(status.equals("0")){
//                return hdfsurl;
//            }else {
//                log.error("upload is error");
//            }
//
//            response.close();
//            httpClient.close();
//        } catch (Exception e) {
//            log.error("sendPost运行,异常:", e.getMessage(), e.getCause());
//        }
//        return responseContent;
//    }
//
//    /**
//     *  带参数的post请求
//     *
//     * @param url
//     * @param body
//     * @return
//     */
//    public static String sendPost(String url, String body) {
//        String responseContent = null;
//        try {
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.addHeader("Content-Type", "application/json");
//            httpPost.setEntity(new StringEntity(body,
//                    StandardCharsets.UTF_8.toString()));
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            responseContent = EntityUtils.toString(entity, StandardCharsets.UTF_8.toString());
//            response.close();
//            httpClient.close();
//        } catch (Exception e) {
//            log.error("sendPost运行,异常:", e.getMessage(), e.getCause());
////            e.printStackTrace();
//        }
//        return responseContent;
//    }
//
//    //特殊的get请求
//    public static String doGet(String url,int WorkSpaceId,String Access_Key) {
//        CloseableHttpClient httpclient = null;
//        CloseableHttpResponse response = null;
//        String result = "";
//        try {
//            httpclient = HttpClients.createDefault();
//            HttpGet httpGet = new HttpGet(url);
//            RequestConfig requestConfig = RequestConfig.custom()
//                    .setConnectTimeout(35000)
//                    .setConnectionRequestTimeout(35000)
//                    .setSocketTimeout(60000)
//                    .build();
//            httpGet.setConfig(requestConfig);
//
//            httpGet.setHeader("Content-Type", "application/json");
//            httpGet.setHeader("Access-Key", Access_Key);
//            httpGet.setHeader("X-Prophet-Workspace-Id", String.valueOf(WorkSpaceId));
//
//            response = httpclient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//            result = EntityUtils.toString(entity);
//        } catch (ClientProtocolException e) {
//            log.error("doGet运行,ClientProtocolException异常:", e.getMessage(), e.getCause());
//        } catch (IOException e) {
//            log.error("doGet运行,IOException异常:", e.getMessage(), e.getCause());
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//                if (null != httpclient) {
//                    httpclient.close();
//                }
//            } catch (IOException e) {
//                log.error("doGet关闭,IOException异常:", e.getMessage(), e.getCause());
//            }
//        }
//        return result;
//    }
//
//
//
//
//
//    //特殊的post请求
//    public static String sendPostDataFile(String url, String body,int WorkSpaceId,String Access_Key) {
//        String responseContent = null;
//        try {
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.addHeader("Content-Type", "application/json");
//            //这个后期可能需要更改
//            httpPost.setHeader("Access-Key",Access_Key);
//            httpPost.addHeader("X-Prophet-Workspace-Id", String.valueOf(WorkSpaceId));
//            httpPost.setEntity(new StringEntity(body, StandardCharsets.UTF_8.toString()));
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            responseContent = EntityUtils.toString(entity, StandardCharsets.UTF_8.toString());
//            response.close();
//            httpClient.close();
//        } catch (Exception e) {
//            log.error("sendPost运行,异常:", e.getMessage(), e.getCause());
////            e.printStackTrace();
//        }
//        return responseContent;
//    }
//    /**
//     * Get请求
//     *
//     * @param url 请求URL
//     * @return
//     */
//    public static String doGet(String url) {
//        CloseableHttpClient httpclient = null;
//        CloseableHttpResponse response = null;
//        String result = "";
//        try {
//            httpclient = HttpClients.createDefault();
//            HttpGet httpGet = new HttpGet(url);
//            RequestConfig requestConfig = RequestConfig.custom()
//                    .setConnectTimeout(35000)
//                    .setConnectionRequestTimeout(35000)
//                    .setSocketTimeout(60000)
//                    .build();
//            httpGet.setConfig(requestConfig);
//
//            response = httpclient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//            result = EntityUtils.toString(entity);
//        } catch (ClientProtocolException e) {
//            log.error("doGet运行,ClientProtocolException异常:", e.getMessage(), e.getCause());
//        } catch (IOException e) {
//            log.error("doGet运行,IOException异常:", e.getMessage(), e.getCause());
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//                if (null != httpclient) {
//                    httpclient.close();
//                }
//            } catch (IOException e) {
//                log.error("doGet关闭,IOException异常:", e.getMessage(), e.getCause());
////                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 带参数的Post请求
//     *
//     * @param url 请求URL
//     * @param map 请求参数
//     * @return
//     */
//    public static String doPostJson(String url, Map map) {
//        CloseableHttpResponse response = null;
//        CloseableHttpClient httpClient = null;
//        String result = "";
//        try {
//
//            httpClient = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost(url);
//            RequestConfig requestConfig = RequestConfig.custom()
//                    .setConnectTimeout(35000)
//                    .setConnectionRequestTimeout(35000)
//                    .setSocketTimeout(60000)
//                    .build();
//            httpPost.setConfig(requestConfig);
//            httpPost.setHeader("Access-Key", (String) map.get(BusDict.AccessKey));
//            httpPost.setHeader("Content-type", "application/json");
//            StringEntity requestEntity = new StringEntity(JSON.toJSONString(map.get(BusDict.UserInfo)), ContentType.APPLICATION_JSON);
//            httpPost.setEntity(requestEntity);
//            response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            result = EntityUtils.toString(entity);
//        } catch (ClientProtocolException e) {
//            log.error("doPostJson运行,ClientProtocolException异常:", e.getMessage(), e.getCause());
//        } catch (IOException e) {
//            log.error("doPostJson运行,IOException异常:", e.getMessage(), e.getCause());
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//                if (null != httpClient) {
//                    httpClient.close();
//                }
//            } catch (IOException e) {
//                log.error("doPostJson关闭,IOException异常:", e.getMessage(), e.getCause());
//            }
//        }
//        return result;
//    }
//
//    public static String doGet(String url, String token) {
//        CloseableHttpClient httpclient = null;
//        CloseableHttpResponse response = null;
//        String result = "";
//        try {
//            httpclient = HttpClients.createDefault();
//            HttpGet httpGet = new HttpGet(url);
//            RequestConfig requestConfig = RequestConfig.custom()
//                    .setConnectTimeout(35000)
//                    .setConnectionRequestTimeout(35000)
//                    .setSocketTimeout(60000)
//                    .build();
//            httpGet.setConfig(requestConfig);
//            httpGet.setHeader("Access-Key", token);
//
//            response = httpclient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//            result = EntityUtils.toString(entity);
//        } catch (ClientProtocolException e) {
//            log.error("doGet运行,ClientProtocolException异常:", e.getMessage(), e.getCause());
//        } catch (IOException e) {
//            log.error("doGet运行,IOException异常:", e.getMessage(), e.getCause());
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//                if (null != httpclient) {
//                    httpclient.close();
//                }
//            } catch (IOException e) {
//                log.error("doGet关闭,IOException异常:", e.getMessage(), e.getCause());
//            }
//        }
//        return result;
//    }
//
//    /**
//     *  不带参数的post请求
//     *
//     * @param url
//     * @param map
//     * @return
//     */
//    public static String sendPost(String url, Map map) {
//        String responseContent = null;
//        CloseableHttpResponse response = null;
//        CloseableHttpClient httpClient = null;
//        try {
//            httpClient = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setHeader("Access-Key", (String) map.get(BusDict.AccessKey));
//            httpPost.setHeader("Content-type", "application/json");
//            response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            responseContent = EntityUtils.toString(entity, StandardCharsets.UTF_8.toString());
//            response.close();
//            httpClient.close();
//        } catch (ClientProtocolException e) {
//            log.error("sendPost运行,ClientProtocolException异常:", e.getMessage(), e.getCause());
//        } catch (IOException e) {
//            log.error("sendPost运行,IOException异常:", e.getMessage(), e.getCause());
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//                if (null != httpClient) {
//                    httpClient.close();
//                }
//            } catch (IOException e) {
//                log.error("sendPost关闭,IOException异常:", e.getMessage(), e.getCause());
//            }
//        }
//        return responseContent;
//    }
//
//    public static String doDelete(String accessKey, String url) throws Exception {
//        CloseableHttpClient client = null;
//        HttpDelete httpDelete = null;
//        CloseableHttpResponse response = null;
//        String result = null;
//        try {
//            client = HttpClients.createDefault();
//            httpDelete = new HttpDelete(url);
//
//            httpDelete.addHeader("Content-type", "application/json; charset=utf-8");
//            httpDelete.setHeader("Accept", "application/json; charset=utf-8");
//            httpDelete.setHeader("Access-Key", accessKey);
////            httpDelete.setEntity(new StringEntity(data));
//
//            response = client.execute(httpDelete);
//            HttpEntity entity = response.getEntity();
//            result = EntityUtils.toString(entity);
//        } catch (ClientProtocolException e) {
//            log.error("doDelete运行,ClientProtocolException异常:", e.getMessage(), e.getCause());
//        } catch (IOException e) {
//            log.error("doDelete运行,IOException异常:", e.getMessage(), e.getCause());
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//                if (null != client) {
//                    client.close();
//                }
//            } catch (IOException e) {
//                log.error("doDelete关闭,IOException异常:", e.getMessage(), e.getCause());
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * 发送 http put 请求，参数以原生字符串进行提交
//     *
//     * @param url
//     * @param map
//     * @return
//     */
//    public static String httpPutJson(String url, Map map) {
//        CloseableHttpResponse response = null;
//        CloseableHttpClient httpClient = null;
//        String result = "";
//        try {
//            httpClient = HttpClients.createDefault();
//            HttpPut httpPut = new HttpPut(url);
//            RequestConfig requestConfig = RequestConfig.custom()
//                    .setConnectTimeout(35000)
//                    .setConnectionRequestTimeout(35000)
//                    .setSocketTimeout(60000)
//                    .build();
//            httpPut.setConfig(requestConfig);
//            if (StringUtils.isNotEmpty((String) map.get(BusDict.UserToken))) {
//                httpPut.setHeader("User-Token", (String) map.get(BusDict.UserToken));
//            }
//            httpPut.setHeader("Access-Key", (String) map.get(BusDict.AccessKey));
//            httpPut.setHeader("Content-type", "application/json");
//            StringEntity requestEntity = new StringEntity(String.valueOf(map.get(BusDict.UserInfo)), ContentType.APPLICATION_JSON);
//            httpPut.setEntity(requestEntity);
//            response = httpClient.execute(httpPut);
//            HttpEntity entity = response.getEntity();
//            result = EntityUtils.toString(entity);
//        } catch (ClientProtocolException e) {
//            log.error("httpPutJson运行,ClientProtocolException异常:", e.getMessage(), e.getCause());
//        } catch (IOException e) {
//            log.error("httpPutJson运行,IOException异常:", e.getMessage(), e.getCause());
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//                if (null != httpClient) {
//                    httpClient.close();
//                }
//            } catch (IOException e) {
//                log.error("httpPutJson关闭,IOException异常:", e.getMessage(), e.getCause());
//            }
//        }
//        return result;
//    }
//}