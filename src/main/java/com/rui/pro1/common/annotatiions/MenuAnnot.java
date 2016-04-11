package com.rui.pro1.common.annotatiions;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 菜单
 * @author liangrui
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD })//类和方法
@Retention(RetentionPolicy.RUNTIME)//动行时保留
@Documented
//@Inherited
public @interface MenuAnnot
{
	public String id() default "-1";

	public String name() default "";

	public String parentId() default "";

	public String client() default "pc";

	public String imgPath() default "";

	public String href() default "";

	public int sortNo() default -1;

}
