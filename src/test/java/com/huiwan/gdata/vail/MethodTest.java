package com.huiwan.gdata.vail;

import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.junit.BeforeClass;
import org.junit.Test;

import com.huiwan.gdata.vail.v.Car;

public class MethodTest {

	static ExecutableValidator executableValidator;

	@BeforeClass
	public static void setUp() {
		// Obtaining an ExecutableValidator instance
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		executableValidator = factory.getValidator().forExecutables();

	}

	@Test
	public void methodT() throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException {

		// Car object = new Car("Morris");

		Class clz = Car.class;

		Object object = clz.newInstance();

		Method method = Car.class.getMethod("drive", int.class, String.class,
				String.class, String.class, BeanVail.class);

		// Object[] parameterValues = { 1,null};
		BeanVail b = new BeanVail();
		b.setAge(10000);
		Object[] parameterValues = { 1, "a", "", "", b };
		// Object[] parameterValues = { 100,"abcdef"};
		Set<ConstraintViolation<Object>> violations = executableValidator
				.validateParameters(object, method, parameterValues);

		System.out.println(violations);
		for (ConstraintViolation cv : violations) {
			System.out.println(cv.getMessage());
		}

	}

	@Test
	public void pp() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		Class clzcar = Car.class;

		Object objectcar = clzcar.newInstance();
		Method methodx = Car.class.getMethod("drive", int.class, String.class,
				String.class, String.class, BeanVail.class);

		BeanVail b = new BeanVail();
		b.setAge(10000);
		Object[] parameterValuestest = { 1, "a", "", "", b };
		// Object[] parameterValues = { 100,"abcdef"};
		Set<ConstraintViolation<Object>> violations = executableValidator.validateParameters(objectcar, methodx,
						parameterValuestest);
		System.out.println(violations);
		for (ConstraintViolation cv : violations) {
			System.out.println(cv.getMessage());
		}


	}
}
