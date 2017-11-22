package com.ibm.opum.resourceutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class ClientConfiguration {

	Logger logger = Logger.getLogger(ClientConfiguration.class);

	private static ClientConfig clientConfig;

	public static Properties props;

	public static String serverURL;

	private ClientConfiguration() {
	}

	public static ClientConfig getInstance() {
		if (clientConfig == null) {
			clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		}

		return clientConfig;
	}

	public static void initProperties() {
		if (props == null) {
			props = new Properties();
			ClassLoader classLoader = ClientConfiguration.class.getClassLoader();
			InputStream in = classLoader.getResourceAsStream("config.properties");
			try {
				props.load(in);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			serverURL = props.getProperty("SERVER_URL");
		}
	}

}
