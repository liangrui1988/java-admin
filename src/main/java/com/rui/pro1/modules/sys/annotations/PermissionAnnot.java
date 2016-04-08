package com.rui.pro1.modules.sys.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Inherited
public @interface PermissionAnnot
{
	public String id() default "-1";
	//public String menuId() default "-1";
	public String name() default "";
	public String cls() default "";
	public int order() default -1;
}
