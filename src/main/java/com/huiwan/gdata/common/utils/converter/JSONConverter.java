package com.huiwan.gdata.common.utils.converter;

import java.net.URLDecoder;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JSONConverter extends AbstractConverter<JSONObject> {

	@Override
	protected JSONObject onResolve(MethodParameter parameter, NativeWebRequest webRequest, ModelAndViewContainer mavContainer, WebDataBinderFactory binderFactory) throws Exception {
		mavContainer.setRequestHandled(true);
		JSONObject json = new JSONObject();
		for (Entry<String, String[]> e : webRequest.getParameterMap().entrySet()) {
			json.put(e.getKey(), URLDecoder.decode(e.getValue()[0], "utf-8"));
		}
		return json;
	}

}
