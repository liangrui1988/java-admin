package com.rui.pro1.modules.sys.exception;

import com.rui.pro1.common.exception.SysRuntimeException;

/**
 * 数据已存在
 * 
 * @author ruiliang
 * @qq 1067165280
 */
public class ObjectExistException extends SysRuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectExistException(String message) {
		super(message);
	}

}
