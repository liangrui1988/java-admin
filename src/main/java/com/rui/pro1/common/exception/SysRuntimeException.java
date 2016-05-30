package com.rui.pro1.common.exception;

public class SysRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SysRuntimeException(String message) {
		super(message);
	}

	public SysRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
