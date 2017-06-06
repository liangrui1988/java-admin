package com.huiwan.gdata.common.utils.json;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON 转换相关的工具类
 */
public class JsonUtils {

	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

	public static final ObjectMapper mapper = new ObjectMapper();

	private static Map<Class, Class> primitiveToPacked = new HashMap<>(9); // 原生类型=>包装类型

	private static final ObjectMapper mapperIgnore = new ObjectMapper();

	public static String toJsonString(Object clz) {
		String json = null;
		try {
			json = mapper.writeValueAsString(clz);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/***
	 * 转对象
	 * 
	 * @param josn
	 * @param clz
	 * @return
	 * @return
	 */
	public static <T> T jsonStreamConverObject(String josn, Class<T> clz) {

		T t = null;
		// ObjectMapper jacksonMapper = new ObjectMapper();
		InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(
				josn.getBytes()));
		BufferedReader streamReader = new BufferedReader(in);
		StringBuilder buff = new StringBuilder();
		String inputStr;
		try {
			while ((inputStr = streamReader.readLine()) != null)
				buff.append(inputStr);
			// ObjectMapper mapper = new ObjectMapper();
			t = mapper.readValue(buff.toString(), clz);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return t;
	}

	/***
	 * 转对象
	 * 
	 * @param josn
	 * @param clz
	 * @return
	 */
	public static <T> T jsonConverObject(String josn, Class<T> clz) {

		T t = null;
		try {
			t = mapper.readValue(josn, clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return t;
	}

	/**
	 * 转集合
	 * 
	 * @param josn
	 * @param clz
	 * @return
	 */
	public static <T> List<T> jsonConverList(String josn, Class<T> clz) {

		List<T> me = null;
		try {
			// jacksonMapper
			// .disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			// jacksonMapper.enableDefaultTyping();
			// jacksonMapper.setVisibility(JsonMethod.FIELD,JsonAutoDetect.Visibility.ANY);
			// jacksonMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
			// false);//格式化
			// jacksonMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			// jacksonMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,
			// false);

			JavaType javaType = mapper.getTypeFactory()
					.constructParametricType(List.class, clz);// clz.selGenType().getClass()

			me = mapper.readValue(josn, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return me;
	}

}
