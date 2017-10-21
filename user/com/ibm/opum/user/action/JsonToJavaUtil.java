package com.ibm.opum.user.action;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonToJavaUtil {

	private static ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
	}

	public static <T> T JsonToJava(String jsonString, Class<T> cls) {
		T result = null;
		try {
			result = mapper.readValue(jsonString, cls);
		} catch (JsonParseException e) {
			System.out.println("Exception Ocurred while converting" + e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("Exception Ocurred while converting" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Exception Ocurred while converting" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
