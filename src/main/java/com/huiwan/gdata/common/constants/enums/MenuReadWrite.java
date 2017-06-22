package com.huiwan.gdata.common.constants.enums;

/**
 * 权限标记，0进行权限控制和写入到数据库
 * 
 * @author ruiliang
 *
 */
public enum MenuReadWrite {

	// Write 如果这个权限标记为只写，就不会做为一个权限拦截了
	// Read 如果这个权限标记为只读，就不会写入到db了，这样可以在同一个方法上，标记一样的权限值，只写入一个时用到
	ReadWrite(0), Read(1), Write(2);

	private int value;

	public int getValue() {
		return value;
	}

	private MenuReadWrite(int i) {
		value = i;
	}
}
