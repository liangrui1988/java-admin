package com.rui.pro1.common.web;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.exception.ErrorCode;
import com.rui.pro1.modules.sys.vo.UserVo;
//@Controller
//@RequestMapping("sys/user")
public class TemplateController {
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getUserList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "page", defaultValue = "20") Integer pagesize,
			UserVo user) {
		ResultBean rb = new ResultBean();
		try {
//			QueryResult<User> result = iUserService.getUserList(page, pagesize,
//					user);
//			rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;

	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean get(HttpRequest request, HttpResponse response) {
		ResultBean rb = new ResultBean();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean del(HttpRequest request, HttpResponse response) {
		ResultBean rb = new ResultBean();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean save(HttpRequest request, HttpResponse response) {
		ResultBean rb = new ResultBean();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpRequest request, HttpResponse response) {
		ResultBean rb = new ResultBean();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}
}
