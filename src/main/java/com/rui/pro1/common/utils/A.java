package com.rui.pro1.common.utils;

public class  A {
	
	Long a=0L;
	
	public synchronized void  x(){
		
		synchronized(a){
			
		}
		
		System.out.println("----");
	}
	

}
