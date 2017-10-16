package com.huiwan.gdata.common.annotatiions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 菜单
 * 
 * @desc 需要jdk8支持
 * 
 * @author liangrui
 * @date 2017/3/23
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD }) // 类和方法
@Retention(RetentionPolicy.RUNTIME) // 动行时保留
@Documented
// @Inherited
public @interface MenuAnnots {
	MenuAnnot[] value();

}
