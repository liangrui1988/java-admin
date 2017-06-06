package com.huiwan.gdata.vail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.bv.MinValidatorForNumber;
import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;

public class SingleVaildation {

	@Test
	public  void test(){
		
		NotNull notnull = null;
		NotNullValidator v=new NotNullValidator();
		//v.initialize(notnull);
		ConstraintValidatorContext cv=new ConstraintValidatorContextImpl(null, null, null, null);
		
		String s="";
		boolean b=v.isValid(s, cv);
		System.out.println(b);
		ConstraintValidator<Min, Number> min=new MinValidatorForNumber();
		
		Min minx=null;
		min.initialize(minx);
		
		int num=-100;
		
		boolean minres=min.isValid(num, null);
		
		System.out.println(minres);
		
		//v.isValid(object, constraintValidatorContext)
		
//		constraintValidatorContext.disableDefaultConstraintViolation();//禁用默认的message的值    
//        //重新添加错误提示语句    
//        constraintValidatorContext    
//        .buildConstraintViolationWithTemplate("字符串不能为空").addConstraintViolation();    
		
	}
}
