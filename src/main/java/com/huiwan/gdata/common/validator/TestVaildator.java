package com.huiwan.gdata.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.huiwan.gdata.common.annotatiions.vali.TsetVaildate;

public class TestVaildator implements ConstraintValidator<TsetVaildate, String>  {

	@Override
	public void initialize(TsetVaildate constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
