package com.rui.pro1.modules.sys.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 
 * @author ruiliang
 *
 */

public class PassUtil {

	public  final static   String salt = "abcd";

	public static String encryptPassword(String password, String name) {

		System.out.println(PassUtil.salt);
		// SHA-1 md5    + name
		String newPassword = new SimpleHash("MD5", password, PassUtil.salt + name, 2)
				.toHex();
		return newPassword;
	}


	public static void main(String[] args) {
		System.out.println(new PassUtil().encryptPassword("admin", "admin"));

	}
}
