package com.huiwan.gdata.modules.sys.exception;

import com.huiwan.gdata.common.exception.SysRuntimeException;

/**
 * 用户已存在
 * 
 * @author ruiliang
 * @qq 1067165280
 */
public class UserExistException extends SysRuntimeException {
	private static final long serialVersionUID = 1L;

	public UserExistException(String message) {
		super(message);
	}

}
