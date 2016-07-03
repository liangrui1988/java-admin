package com.rui.pro1.vail;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path.MethodNode;
import javax.validation.Path.Node;
import javax.validation.Path.ParameterNode;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.junit.BeforeClass;
import org.junit.Test;

import com.rui.pro1.vail.v.Car;

public class MethodTest {

	static ExecutableValidator executableValidator;

	@BeforeClass
	public static void setUp() {
		// Obtaining an ExecutableValidator instance
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		executableValidator = factory.getValidator().forExecutables();

	}

	@Test
	public void methodT() throws NoSuchMethodException, SecurityException {

		Car object = new Car("Morris");
		Method method = Car.class.getMethod("drive", int.class, String.class);

		// Object[] parameterValues = { 1,null};
		Object[] parameterValues = { 1, "a" };
		// Object[] parameterValues = { 100,"abcdef"};
		Set<ConstraintViolation<Car>> violations = executableValidator
				.validateParameters(object, method, parameterValues);

		System.out.println(violations);
		for (ConstraintViolation cv : violations) {
			System.out.println(cv.getMessage());
		}

	}
}
