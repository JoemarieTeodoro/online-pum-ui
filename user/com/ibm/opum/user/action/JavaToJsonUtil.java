package com.ibm.opum.user.action;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JavaToJsonUtil {
	private static ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();

	}
	public static String javaToJson(Object object){
		String jsonResult = null;
		
		 try {
			jsonResult= mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			System.out.println("Exception Ocurred while converting"+ e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("Exception Ocurred while converting"+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Exception Ocurred while converting"+ e.getMessage());
			e.printStackTrace();
		}
	return jsonResult;
	}
	
	
}
