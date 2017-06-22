package com.huiwan.gdata.common.annotatiions.vali;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.huiwan.gdata.common.validator.TestVaildator;

@Constraint(validatedBy = TestVaildator.class)
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodVailAnnot {

	/**
	 * 这里只做对方法里的单个字段上面有注解的验证，暂不考虑@Valid 一起验证功能,<br>
	 * 这里填字符串'null'跳过就好，后期可扩展一起验证
	 * <p>
	 * 对象验证功能在另一解析器里做验证，最后合并结果操作，请参考{@link BeanVaildate}
	 * <p>
	 * 例:public void myMethod(@notNull String name,@max(100)int age,String
	 * noVail,myBean bean){}<br>
	 * 则传:{'name','age','','null'}
	 * 
	 * @return 方法参数对应的参数的名字，不需要验证的对象写'' 对象写'null'
	 */
	String[] methodParamName() default {};

}
