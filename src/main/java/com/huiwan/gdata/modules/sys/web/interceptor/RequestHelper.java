package com.huiwan.gdata.modules.sys.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestHelper {

//    public static KMessage getKMessage() {
//        return KMessage.getInstance(getRequest(), getResponse());
//    }

//    public static HttpServletResponse getResponse() {
//        return (HttpServletResponse) getRequest().getAttribute(ResponseAwareInterceptor.RESPONSE);
//    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        return attributes.getRequest();
    }

//    public static boolean isJsonRequest() {
//        Boolean jsonRequest = (Boolean) getRequest().getAttribute(Constants.REQ_JSON);
//        return jsonRequest != null && jsonRequest;
//    }

//    public static MMessage getMMessage() throws Exception {
//        return MMessage.getInstance(getRequest(), getResponse());
//    }
}
