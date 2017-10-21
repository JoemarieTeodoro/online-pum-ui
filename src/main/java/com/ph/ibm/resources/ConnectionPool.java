package com.ph.ibm.resources;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.ph.ibm.util.OpumConstants;

/**
 * This class contains connection to mysql database.
 */
public class ConnectionPool {

	Logger logger = Logger.getLogger(ConnectionPool.class);

	private static ConnectionPool connectionPool = new ConnectionPool();

	private ConnectionPool() {

	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionPool getInstance() {
		return connectionPool;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/opum", "root", "root");
		} catch (Exception e) {
			logger.error(OpumConstants.UNABLE_TO_ESTABLISH_CONNECTION);
			e.printStackTrace();
		}
		return connection;
	}
}