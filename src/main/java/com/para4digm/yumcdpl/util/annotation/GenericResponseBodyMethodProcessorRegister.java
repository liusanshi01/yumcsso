package com.para4digm.yumcdpl.util.annotation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericResponseBodyMethodProcessor注册器，将GenericResponseBodyMethodProcessor注册到RequestMappingHandlerAdapter
 * @author:
 * Created on 2016年3月7日 上午10:39:22
 * @modify author:修改人
 * Modify on 修改时间
*/
@Component
public class GenericResponseBodyMethodProcessorRegister implements InitializingBean {

	@Autowired
	private RequestMappingHandlerAdapter adapter;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		List<HttpMessageConverter<?>> messageConverters = adapter.getMessageConverters();
		List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<HandlerMethodReturnValueHandler>(adapter.getReturnValueHandlers());
		
		/**将GenericResponseBodyMethodProcessor注册到RequestMappingHandlerAdapter*/
		GenericResponseBodyMethodProcessor processor = new GenericResponseBodyMethodProcessor(messageConverters);
		returnValueHandlers.add(0, processor);
		adapter.setReturnValueHandlers(returnValueHandlers);
	}
}
