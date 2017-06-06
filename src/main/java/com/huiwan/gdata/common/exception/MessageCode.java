package com.huiwan.gdata.common.exception;

public class MessageCode {
	// ---------------------------------------
	// ----------------系统操作消息码--------
	// ---------------------------------------
	public static final String SYS_SUCCESS = "001";// 成功
	public static final String SYS_NO_USER = "002";// 用户不存在
	public static final String SYS_NO_USER_AND_PASSWORD = "003";// 用户或密码错误
	public static final String SYS_NO_PERMISSE = "004";// 没有权限

	public static final String SYS_ERROR = "005";// 系统异常
	public static final String SYS_FAILURE = "006";// 操作失败
	public static final String SYS_LOG_IN_TOO_MANY = "007";// 账户错误次数过多,暂时禁止登录!
	public static final String SYS_VERIFICATION_CODE_ERROR = "008";// 验证码错误

	public static final String ARGUMENT_ILLEGAL = "009";// 系统参数不合法

	public static final String PLASS_LOGIN = "010";// 请登陆系统
	public static final String USER_LOGOUT = "011";// 退出系统成功
	
	public static final String PLASS_CAPTCHA = "012";// 请输入验证码
	public static final String CAPTCHA_ERROR = "013";// 验证码不正确


	// ---------------------------------------
	// ----------------用户操作消息码--------
	// ---------------------------------------
	public static final String USER_EXISTS = "100";// 用户已存在
	public static final String USER_REPEAT_PASSWORD_ERROR = "101";// 用户密码不一致
	public static final String USER_SRC_PASSWORD_ERROR = "102";// 原始密码错误

	
	public static final String ROLE_EXISTS = "110";// 角色已存在

	// ---------------------------------------
	// ----------------xx业务操作消息码--------
	// ---------------------------------------
	public static final String OBJECT_EXIST_EXCEPTION = "120";// 数据已存在

}
