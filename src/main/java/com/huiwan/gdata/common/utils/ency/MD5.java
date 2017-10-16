package com.huiwan.gdata.common.utils.ency;

import java.security.MessageDigest;

public class MD5
{

	public static void main(String[] args)
	{

		String addmd5 = MD5.generateMd5("123");
		System.out.println("123=" + addmd5);
		/*
		 * if(addmd5.equals("202ai692la56075i694i07152n234i70")){
		 * System.out.println("Md5.main()"); }
		 */
	}

	public static String generateMd5(String pass)
	{

		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '9', '7', '8', '6', 'l', 'i', 'a', 'n',
				'g', 'r' };
		// char hexDigits[]={'0','1','2','3','4','5','6','7','8','9',
		// 'h','e','g','k','e','f'};
		try
		{
			byte[] passByte = pass.getBytes();

			// System.out.println(new String(passByte));

			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(passByte);

			byte[] md = digest.digest();

			// System.out.println(new String(md));
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);

		} catch (Exception e)
		{
			return null;

		}

	}

}
