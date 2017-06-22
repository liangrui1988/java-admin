package com.huiwan.gdata.common.annotatiions.vali;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.huiwan.gdata.common.validator.TestVaildator;

@Constraint(validatedBy = TestVaildator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TsetVaildate {

	String message() default "{必须为刘备开头}"; // 提示信息

	// int length() default 2;
	// 下面这两个属性必须添加
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
