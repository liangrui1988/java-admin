package com.huiwan.gdata.common.utils.date;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 自定义json转换 日期
 * 
 * @JsonSerialize(using=DateSerializer.class)
 * 
 * @author 1067165280
 *
 */

public class DateSerializer extends JsonSerializer<Date> {
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeString(formatter.format(value));
	}
}
