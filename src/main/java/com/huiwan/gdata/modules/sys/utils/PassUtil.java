package com.huiwan.gdata.modules.sys.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

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
		//String newPassword = new SimpleHash("md5", password, PassUtil.salt + name, 2).toHex();
		//String newPassword = new SimpleHash("MD5", password, PassUtil.salt + name).toHex();
		
		//要和验证时保留一致，大坑
		ByteSource bs=ByteSource.Util.bytes(PassUtil.salt	+ name);
		String newPassword = new SimpleHash("md5", password, bs, 2).toHex();

		System.out.println(newPassword);
		return newPassword;
	}


	public static void main(String[] args) {
		System.out.println(PassUtil.encryptPassword("admin", "admin"));
		
		ByteSource bs=ByteSource.Util.bytes("admin");
		String newPassword = new SimpleHash("md5", "admin", bs).toHex();
		System.out.println(newPassword);

		String newPassword2 = new SimpleHash("md5",  bs).toHex();

		System.out.println(newPassword2);
		

	}
}
