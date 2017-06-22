package com.huiwan.gdata.modules.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huiwan.gdata.modules.test.bean.BeanTest;

@RequestMapping(value = "test")
public class DateTestController {

	// @InitBinder
	// public void initBinder(WebDataBinder binder) throws Exception {
	// // 注册自定义的属性编辑器
	// // 1、日期
	// DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	// CustomDateEditor dateEditor = new CustomDateEditor(df,
	// true);//true:允许输入空值，false:不能为空值
	// // 表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
	// binder.registerCustomEditor(Date.class, dateEditor);
	// // binder.registerCustomEditor(PhoneNumberModel.class,
	// // new PhoneNumberEditor());
	// }

	// @InitBinder
	// protected void initBinder(WebDataBinder binder) {
	// binder.registerCustomEditor(Date.class, new CustomDateEditor(new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	// binder.registerCustomEditor(int.class, new CustomNumberEditor(int.class,
	// true));
	// binder.registerCustomEditor(int.class, new IntegerEditor());
	// binder.registerCustomEditor(long.class, new
	// CustomNumberEditor(long.class, true));
	// binder.registerCustomEditor(long.class, new LongEditor());
	// binder.registerCustomEditor(double.class, new DoubleEditor());
	// binder.registerCustomEditor(float.class, new FloatEditor());
	// }
	/**
	 * http://127.0.0.1:9808/test/getEditor?id=10&money=45.88&time=2016-12-10
	 * 
	 * @param BeanTest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getEditor")
	public Map getEditor(BeanTest BeanTest) {

		System.out.println("getEditor>>" + BeanTest);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("k", BeanTest);

		System.out.println(BeanTest);
		return map;

	}
}
