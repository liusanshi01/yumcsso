package com.para4digm.yumcdpl.util.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 通用响应体方法处理器，将方法标注为{@link GenericResponseBody}的返回值，包装成{@link GenericResponse}返回给客户端。
 * @author:
 * Created on 2016年3月7日 上午10:23:57
 * @modify author:修改人
 * Modify on 修改时间
*/
public class GenericResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {
	
	private static final String REQ_ID_PARAM_NAME = "method";
	
	/**请求id在request中的参数名称*/
	private String reqIdParamName = REQ_ID_PARAM_NAME;
	
	public GenericResponseBodyMethodProcessor(List<HttpMessageConverter<?>> messageConverters) {
		super(messageConverters);
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return false;
	};
	

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return ((AnnotationUtils.findAnnotation(returnType.getContainingClass(), GenericResponseBody.class) != null) ||
				(returnType.getMethodAnnotation(GenericResponseBody.class) != null));
	}

	@Override
	public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws HttpMediaTypeNotAcceptableException, IOException {
		
		/**包装方法返回的bean*/
		final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		String method = request.getParameter(reqIdParamName);
		GenericResponse<Object> wrapper = new GenericResponse<>(method, returnValue);
		RmpHolder.setResponse(wrapper);
		super.handleReturnValue(wrapper, returnType, mavContainer, webRequest);
	}
	
	public void setReqIdParamName(String reqIdParamName) {
		this.reqIdParamName = reqIdParamName;
	}
}
