package com.ibm.opum.resourceutils;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class ClientConfiguration {

	Logger logger = Logger.getLogger(ClientConfiguration.class);

	private static ClientConfig clientConfig;

	private ClientConfiguration() {
	}

	public static ClientConfig getInstance() {
		if (clientConfig == null) {
			clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		}

		return clientConfig;
	}

}
