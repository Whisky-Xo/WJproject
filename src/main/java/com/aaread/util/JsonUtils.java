package com.aaread.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();
	static {
		// mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
	}

	public static String toJson(Object o) {
		try {
			String r = mapper.writeValueAsString(o);
			return r;
		} catch (Exception e) {
			throw new JsonException(e);
		}
	}

	public static <T> T fromJson(String src, Class<T> t) {
		try {
			return mapper.readValue(src, t);
		} catch (Exception e) {
			throw new JsonException(e);
		}
	}

	public static <T> T fromJson(String json, Class<T> valueType,
			String... keys) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			JsonNode node = mapper.readTree(json);
			int len = keys.length;
			int index = 0;
			while (len > 0) {
				if (!node.isContainerNode()) {
					return null;
				}
				node = node.get(keys[index]);
				len--;
				index++;
			}
			return mapper.readValue(node.toString(), valueType);
		} catch (Exception e) {
			throw new JsonException(e);
		}
	}
	

	public static <T> T fromJson(String json, Class<T> valueType,Class valueType2,
			String... keys) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			JsonNode node = mapper.readTree(json);
			int len = keys.length;
			int index = 0;
			while (len > 0) {
				if (!node.isContainerNode()) {
					return null;
				}
				node = node.get(keys[index]);
				len--;
				index++;
			}
			JavaType javaType = getCollectionType(valueType, valueType2); 
			return mapper.readValue(node.toString(), javaType);
		} catch (Exception e) {
			throw new JsonException(e);
		}
	}

	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getFromJson(String json, String... keys) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		JSONObject obj = (JSONObject) JSONValue.parse(json);
		JSONObject data = null;
		if (obj == null) {
			return null;
		}
		for (int i = 0; i < keys.length; i++) {
			Object value = obj.get(keys[i]);
			if (i == keys.length - 1) {
				return (T) value;
			}
			if (value == null) {
				return null;
			}
			data = (JSONObject) value;
			obj = data;
		}

		return null;
	}

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		String url = "http://localhost/total_lixi.json?lend_money=1000000&year_rerest=120&bill_num=3&bill_type=301";
		try {
			String result = HttpClientUtils.getHttpResult(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		String readValue = fromJson("1", String.class);
		System.out.println(readValue);
	}

}

@SuppressWarnings("serial")
class JsonException extends RuntimeException {

	public JsonException(Exception e) {
		super(e);
	}

}
