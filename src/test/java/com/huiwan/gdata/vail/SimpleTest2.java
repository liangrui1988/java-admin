package com.huiwan.gdata.vail;

import static org.junit.Assert.assertNotNull;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleTest2 {

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
	
	
	
	@Length(min = 2, max = 6) 
	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	@Test
	public void testString()
	 {
//		MethodValidator methodValidator = Validation.byProvider( HibernateValidator.class )
//			    .configure()
//			    .buildValidatorFactory()
//			    .getValidator()
//			    .unwrap( MethodValidator.class ); 
	
	 }

	@Test
	public void test(@Length(min = 2, max = 6) String ss)
	 {
		
	
	}
}
