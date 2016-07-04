package com.rui.pro1.vail;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;

public class SingleVaildation {

	@Test
	public  void test(){
		
		NotNull notnull = null;
		
		NotNullValidator v=new NotNullValidator();
		v.initialize(notnull);
		
		ConstraintValidatorContext cv=new ConstraintValidatorContextImpl(null, null, null, null);
		
		//v.isValid(object, constraintValidatorContext)
		
//		constraintValidatorContext.disableDefaultConstraintViolation();//禁用默认的message的值    
//        //重新添加错误提示语句    
//        constraintValidatorContext    
//        .buildConstraintViolationWithTemplate("字符串不能为空").addConstraintViolation();    
		
	}
}
