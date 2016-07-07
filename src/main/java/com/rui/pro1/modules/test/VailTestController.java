package com.rui.pro1.modules.test;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.annotatiions.vali.BeanVaildate;
import com.rui.pro1.modules.sys.constants.SysComm;

@Controller
@RequestMapping(value = "test")
public class VailTestController {

	// @BeanVaildate

	@ResponseBody
	@RequestMapping(value = "testVaildate")
	public Map testVaildate(HttpServletRequest req, @BeanVaildate SaleAgent entity,
			@Null String name, @NotNull String hello, @Max(value=100) int size,
			@Min(value=10,message="size2值必须最小为10")Integer size2,@Length(min=1,max=5)String lengthT, Model model) {
		Map<String, String> map = new HashMap<String, String>();

		Object obj = req.getAttribute(SysComm.VAIL_ERROR_MESSAGE);
		if (obj != null) {
			map = (Map<String, String>) obj;
			System.out.println(map);
			return map;
		}

		try {
			// BeanValidators.validateWithException(validator,entity);
			// BeanValidators.validateWithException(validator,hello);
			// BeanValidators.validateWithException(validator,size);

		} catch (ConstraintViolationException ex) {
			// List<String> list =
			// BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			// list.add(0, "数据验证失败：");
			//
			// System.out.println(list);
			// addMessage(model, list.toArray(new String[]{}));

		}

		System.out.println(entity.getName());
		System.out.println(hello);
		System.out.println(size);
		map.put("ok", "通过");
		map.put("hello", hello);
		return map;
	}

//	@ResponseBody
//	@RequestMapping(value = "tvx")
//	@MethodVailAnnot(methodParamName = {  "name", "hello",
//			"size", "size2"})
//	public Map tvx(String name, @NotNull String hello,
//			@Max(100) int size, Integer size2) {
//		Map<String, String> map = new HashMap<String, String>();
//
//
//		System.out.println(hello);
//		System.out.println(size);
//		map.put("ok", "通过");
//		map.put("hello", hello);
//		return map;
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "testVaildate2")
//	public Object testVaildate2(@BeanVaildate SaleAgent entity,
//			@NotNull String hello, @Length(min = 1, max = 200) int size) {
//		// System.out.println(entity);
//		System.out.println(hello);
//		System.out.println(size);
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		map.put("h", hello);
//		map.put("s", size);
//
//		return map;
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "testVaildate3")
//	public Object testVaildate3(String hello, int size) {
//		// System.out.println(entity);
//		System.out.println(hello);
//		System.out.println(size);
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		map.put("h", hello);
//		map.put("s", size);
//
//		return map;
//	}

	public static void main(String[] args) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.print(format.format(DateUtils.addDays(new Date(),182)));
	}
	
	/**
	 * Bean Validation 中内置的 constraint   
    
@Null   被注释的元素必须为 null   
@NotNull    被注释的元素必须不为 null   
@AssertTrue     被注释的元素必须为 true   
@AssertFalse    被注释的元素必须为 false   
@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值   
@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值   
@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值   
@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值   
@Size(max=, min=)   被注释的元素的大小必须在指定的范围内   
@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内   
@Past   被注释的元素必须是一个过去的日期   
@Future     被注释的元素必须是一个将来的日期   
@Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式   
   
Hibernate Validator 附加的 constraint   
@NotBlank(message =)   验证字符串非null，且长度必须大于0   
@Email  被注释的元素必须是电子邮箱地址   
@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内   
@NotEmpty   被注释的字符串的必须非空   
@Range(min=,max=,message=)  被注释的元素必须在合适的范围内
	 */
}
