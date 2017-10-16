package com.huiwan.gdata.common.utils.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ResponseNullUtil extends ObjectMapper {
	private static final long serialVersionUID = 1L;

	//
	public ResponseNullUtil() {
		// 空值处理为空串
		this.getSerializerProvider().setNullValueSerializer(
				new JsonSerializer<Object>() {
					@Override
					public void serialize(Object value, JsonGenerator jg,
							SerializerProvider sp) throws IOException,
							JsonProcessingException {
						jg.writeString("");
					}
				});

	}
	// public ObjectMappingCustomer()
	// {
	// super();
	// // 允许单引号
	// this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	// // 字段和值都加引号
	// this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	// // 数字也加引号
	// this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
	// this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
	// // 进行HTML解码。
	// this.registerModule(new SimpleModule().addSerializer(String.class, new
	// JsonSerializer<String>(){
	// @Override
	// public void serialize(String value, JsonGenerator jgen,
	// SerializerProvider provider) throws IOException,
	// JsonProcessingException {
	// jgen.writeString(StringEscapeUtils.unescapeHtml4(value));
	// }
	// }));
	// // 设置时区
	// this.setTimeZone(TimeZone.getDefault());//getTimeZone("GMT+8:00")
	// // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
	// this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

}
