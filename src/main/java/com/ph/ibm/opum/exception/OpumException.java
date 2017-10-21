package com.ph.ibm.opum.exception;

import org.apache.log4j.Logger;

public class OpumException extends Exception {
	private Logger logger = Logger.getLogger(OpumException.class);
	private static final long serialVersionUID = 1L;
	private String message = null;

	public OpumException() {
		super();
	}

	public OpumException(String message) {
		super(message);
		this.message = message;
		logger.error(message);
	}

	public OpumException(Throwable cause) {
		super(cause);
		logger.error(cause);
	}

	public OpumException(String message, Throwable cause) {
		super(message, cause);
		logger.error(cause);
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}