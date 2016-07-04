package com.rui.pro1.modules.test;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.annotatiions.vali.BeanVaildate;
import com.rui.pro1.common.annotatiions.vali.MethodVailAnnot;
import com.rui.pro1.modules.sys.constants.SysComm;

@Controller
@RequestMapping(value = "test")
public class VailTestController {

	// @BeanVaildate

	@ResponseBody
	@RequestMapping(value = "testVaildate")
	@MethodVailAnnot(methodParamName = { "null", "null", "name", "hello",
			"size", "size2", "null" })
	public Map testVaildate(HttpServletRequest req, SaleAgent entity,
			String name, @NotNull String hello, @Max(100) int size,
			Integer size2, Model model) {
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

	@ResponseBody
	@RequestMapping(value = "tvx")
	@MethodVailAnnot(methodParamName = {  "name", "hello",
			"size", "size2"})
	public Map tvx(String name, @NotNull String hello,
			@Max(100) int size, Integer size2) {
		Map<String, String> map = new HashMap<String, String>();


		System.out.println(hello);
		System.out.println(size);
		map.put("ok", "通过");
		map.put("hello", hello);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "testVaildate2")
	public Object testVaildate2(@BeanVaildate SaleAgent entity,
			@NotNull String hello, @Length(min = 1, max = 200) int size) {
		// System.out.println(entity);
		System.out.println(hello);
		System.out.println(size);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("h", hello);
		map.put("s", size);

		return map;
	}

	@ResponseBody
	@RequestMapping(value = "testVaildate3")
	public Object testVaildate3(String hello, int size) {
		// System.out.println(entity);
		System.out.println(hello);
		System.out.println(size);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("h", hello);
		map.put("s", size);

		return map;
	}

	public static void main(String[] args) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.print(format.format(DateUtils.addDays(new Date(),182)));
	}
}
