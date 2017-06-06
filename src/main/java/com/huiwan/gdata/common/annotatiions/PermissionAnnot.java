package com.huiwan.gdata.common.annotatiions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.huiwan.gdata.common.constants.enums.MenuReadWrite;

/**
 * 简单实现权限，扫描到DB和访问控制<br/>
 * 如果要实现复杂权限控和角色等等<br/>
 * 请使用shiro内置权限控制注解<br/>
 * {@link RequiresPermissions}
 * 
 * @author rui
 * @date 2016/06/09
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// @Inherited
public @interface PermissionAnnot {
	public String id() default "-1";//权限标识 

	// public String menuId() default "-1";
	public String name() default "";

	public String cls() default "";

	public int order() default -1;

	public MenuReadWrite readWrite() default MenuReadWrite.ReadWrite;// 菜单 控制

	/**
	 * The permission string which will be passed to
	 * {@link org.apache.shiro.subject.Subject#isPermitted(String)} to determine
	 * if the user is allowed to invoke the code protected by this annotation.
	 */
	// String[] value();

	/**
	 * The logical operation for the permission checks in case multiple roles
	 * are specified. AND is the default
	 * 
	 * @since 1.1.0
	 */
	// Logical logical() default Logical.AND;

}
