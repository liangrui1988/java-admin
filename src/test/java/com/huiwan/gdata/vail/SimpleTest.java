package com.huiwan.gdata.vail;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.MethodValidator;

public class SimpleTest {

	private static Validator validator;
	
	private static ValidatorImpl ValidatorImpl;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testBuildDefaultValidatorFactory() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		assertNotNull(validator);
	}
	
	
	String ss;
	
	
	
	
	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	//@Length(min = 2, max = 6) String ss
	@Test
	public void testString()
	 {

		
//	
//		validator.validateValue(String.class, "ss", "a");
//		
//		Set<ConstraintViolation<String>> methodV2=validator.validate(ss);
//		System.out.println("methodV2:"+methodV2);
//		for(ConstraintViolation cv:methodV2){
//			System.out.println(cv.getMessage());
//		}
	 }

	@Test
	public void test(@Length(min = 2, max = 6) String ss)
	 {
		
		BeanVail bean=new BeanVail();
		
		bean.setName("hello");
		
		bean.setFullName("a");
		
		bean.setAge(1000000);
		
		Set<ConstraintViolation<BeanVail>> set1=validator.validate(bean);
		
		System.out.println("set1:"+set1);
		for(ConstraintViolation cv:set1){
			System.out.println(cv.getMessage());
		}
		
		Set<ConstraintViolation<BeanVail>> set=validator.validateProperty(bean, "name");
		
		for(ConstraintViolation cv:set){
			System.out.println(cv.getMessage());
		}	
		
		
		
		ExecutableValidator executable=validator.forExecutables();
		
		System.out.println("set:"+set);
		// Set<ConstraintViolation<TwoPasswords>> constraintViolations =
		// validator
		// .validate(ss);
		// for (ConstraintViolation<TwoPasswords> constraintViolation :
		// constraintViolations) {
		// System.out.println(constraintViolation.getMessage());
		// }
	}
}
