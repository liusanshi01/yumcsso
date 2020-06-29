package com.para4digm.yumcdpl.util.annotation;

import org.springframework.core.NamedInheritableThreadLocal;

/**
 * 模型平台当前线程全局变量持有者
 */
public class RmpHolder {
    private final static ThreadLocal<GenericResponse> responseHolder = new NamedInheritableThreadLocal<GenericResponse>("GenericeResponse");

    public static void setResponse(GenericResponse response){
        responseHolder.set(response);
    }
    public static GenericResponse getResponse(){
        return responseHolder.get();
    }
    public static void removeResponse(){
        responseHolder.remove();
    }


}
