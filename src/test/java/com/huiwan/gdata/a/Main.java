package com.huiwan.gdata.a;

public class Main {

	
	public static void main(String[] args) {
		 int[] res = new int[]{0,1,2,3,4};

		 int[] to = new int[]{5,6,7,8,9};

		System.out.println(res);
		System.out.println(to);

		 

		 System.arraycopy(res, 0, to, 0, 5);

		 System.out.println("--copy整个数组");

		System.out.println(to);

		 

		 System.arraycopy(res, 1, to, 2, 3);

		 System.out.println("--copy 源数组1开始3个数 到to的2开始3个数");

		System.out.println(to);

	}
}
