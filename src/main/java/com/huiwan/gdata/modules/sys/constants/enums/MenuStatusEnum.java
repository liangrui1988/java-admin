package com.huiwan.gdata.modules.sys.constants.enums;


public enum MenuStatusEnum {
	NORMAL_0(0, "正常"), PERMISSION_1(1, "权限标识"), STOP_2(2, "停用");

	private int code;
	private String value;

	public String getValue() {
		return value;
	}

	public int getCode() {
		return code;
	}

	private MenuStatusEnum(int code, String value) {
		this.value = value;
		this.code = code;
	}

	// 根据code 获取value
	public static String getValue(int code) {
		for (MenuStatusEnum c : MenuStatusEnum.values()) {
			if (c.getCode() == code) {
				return c.getValue();
			}
		}
		return null;
	}
}
