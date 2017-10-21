package com.ph.ibm.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * This class is used to convert Java Object into JSON
 */
public class JavaToJsonUtil {
	private static ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
	}

	public static String JavaToJson(Object object) {
		String jsonResult = null;

		try {
			jsonResult = mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			System.out.println("Exception Ocurred while converting" + e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("Exception Ocurred while converting" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Exception Ocurred while converting" + e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
	}

}
