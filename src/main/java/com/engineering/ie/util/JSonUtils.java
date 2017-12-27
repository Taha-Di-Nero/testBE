package com.engineering.ie.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * Created by Taha on 22/10/2017.
 */

public class JSonUtils {

	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	public static String toJSon(Object instance) throws JsonProcessingException {
		return mapper.writeValueAsString(instance);
	}

	public static <T> T fromJSon(String jsonMsg, Class<T> type) throws IOException {
		return mapper.readValue(jsonMsg, type);
	}
}
