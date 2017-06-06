package com.huiwan.gdata.modules.sys.exception;

import com.huiwan.gdata.common.exception.SysException;

/**
 * 数据已存在
 * 
 * @author ruiliang
 * @qq 1067165280
 */
public class ObjectExistException extends SysException {
	private static final long serialVersionUID = 1L;

	public ObjectExistException(String message) {
		super(message);
	}

}
