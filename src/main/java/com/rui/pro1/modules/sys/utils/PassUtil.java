package com.rui.pro1.modules.sys.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ruiliang
 *
 */
//@Component
public class PassUtil {
	
	//FIXME:注入不进
	@Value("${sys.user.key}")
	public  final static String salt = "abcd";

	public static String encryptPassword(String password, String name) {

		System.out.println(salt);
		// SHA-1 md5    + name
		String newPassword = new SimpleHash("MD5", password, salt + name, 2)
				.toHex();

		return newPassword;
	}


	public static void main(String[] args) {
		System.out.println(new PassUtil().encryptPassword("admin", "admin"));

	}
}
