package com.huiwan.gdata.vail.ssist;

//import java.lang.annotation.Annotation;

import java.util.Set;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

import org.hibernate.validator.constraints.Length;
import org.junit.Test;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("ddd");
	}
	
	@Test  
	public void ReadTest() throws NotFoundException{  
	    ClassPool pool = ClassPool.getDefault();  
	    //获取要修改的类的所有信息  
	    CtClass ct = pool.get("com.huiwan.gdata.vail.ssist.CollectionBase");     
	    //获取类中的方法  
	    CtMethod[] cms = ct.getDeclaredMethods();         
	     //获取第一个方法（因为只有一个方法）  
	     CtMethod cm = cms[0];        
	     System.out.println("方法名称====" + cm.getName());        
	     //获取方法信息  
	     MethodInfo methodInfo = cm.getMethodInfo();           
	     //获取类里的em属性  
	     CtField cf = ct.getField("em");  
	     //获取属性信息  
	     FieldInfo fieldInfo = cf.getFieldInfo();          
	     System.out.println("属性名称===" + cf.getName());  
	       
	     //获取注解属性  
	     AnnotationsAttribute attribute = (AnnotationsAttribute) fieldInfo.getAttribute(AnnotationsAttribute.visibleTag);  
	     System.out.println("attribute:"+attribute);   
	     //获取注解  
	     Annotation annotation = attribute.getAnnotation("org.hibernate.validator.constraints.Length");         
	     System.out.println("annotation:"+annotation);  
	     //获取注解的值  
	     Set set=annotation.getMemberNames();
	     System.out.println(set);
	     String text =((StringMemberValue) annotation.getMemberValue("max")).getValue() ;         
	     System.out.println("注解名称===" + text);  
	       
	}  

}
