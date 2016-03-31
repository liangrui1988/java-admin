package com.rui.pro1.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.service.IUserService;
import com.rui.pro1.modules.sys.vo.UserVo;

/**
 * 用户管理
 * 
 * @author ruiliang
 *
 */
@Controller
@RequestMapping("sys/user")
public class UserController {
	@Autowired
	private IUserService iUserService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getUserList(@RequestParam(value="page",defaultValue="1")Integer page, @RequestParam(value="page",defaultValue="20")Integer pagesize, UserVo user) {
		ResultBean rb = new ResultBean();
		try {
			QueryResult<User> result = iUserService.getUserList(page, pagesize, user);
			rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, "001", "异统异常");
		}
		return rb;

	}

}
