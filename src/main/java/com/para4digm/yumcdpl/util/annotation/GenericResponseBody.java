package com.para4digm.yumcdpl.util.annotation;

import java.lang.annotation.*;

/**
 * 通用响应体注解
 * @author:
 * Created on 2016年3月7日 上午9:59:01
 * @modify author:修改人
 * Modify on 修改时间
*/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenericResponseBody {

}
