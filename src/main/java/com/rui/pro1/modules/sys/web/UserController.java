package com.rui.pro1.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.common.exception.ErrorCode;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.service.IUserService;
import com.rui.pro1.modules.sys.vo.UserVo;

/**
 * 用户管理
 * 
 * @author ruiliang
 * @date 2016/04/05
 *
 */
@Controller
@RequestMapping("sys/user")
public class UserController extends SysBaseControoler {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getUserList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "page", defaultValue = "20") Integer pagesize,
			UserVo user) {
		ResultBean rb = new ResultBean();
		try {
			QueryResult<User> result = userService.getUserList(page, pagesize,
					user);
			rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;

	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean get(HttpServletRequest request, HttpServletResponse response,
			UserVo userVo) {
		ResultBean rb = new ResultBean();
		try {
			User user = userService.get(userVo.getId());
			rb.setData(user);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean del(HttpRequest request, HttpResponse response,
			@RequestParam(required = false, value = "id") Integer id) {
		ResultBean rb = new ResultBean();
		try {
			int count = userService.del(id);
			if (count <= 0) {
				rb = new ResultBean(false, ErrorCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean add(HttpServletRequest request, HttpServletResponse response, User user) {
		ResultBean rb = new ResultBean();
		try {
			int count = userService.add(user);
			if (count <= 0) {
				rb = new ResultBean(false, ErrorCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpServletRequest request, HttpServletResponse response,
			User user) {
		ResultBean rb = new ResultBean();
		try {
			int count = userService.update(user);
			if (count <= 0) {
				rb = new ResultBean(false, ErrorCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

}
