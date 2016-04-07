package com.rui.pro1.modules.sys.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.exception.ErrorCode;
import com.rui.pro1.modules.sys.bean.UserBean;
import com.rui.pro1.modules.sys.service.IUserLoginService;
import com.rui.pro1.modules.sys.vo.UserLoginVo;

@Controller
@RequestMapping("sys/user")
public class UserLoginController extends SysBaseControoler {
//	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserLoginService userLoginService;

	@RequestMapping(value = "login")//, method = RequestMethod.POST
	@ResponseBody
	public ResultBean login(HttpServletRequest request, HttpServletResponse response,
			UserLoginVo userLoginVo) {
		ResultBean rb = new ResultBean();
		try {
			logger.warn("message:{}",userLoginVo);
			UserBean userBean = userLoginService.login(userLoginVo);
			
			if(userBean==null||userBean.getId()<=0){
				rb = new ResultBean(false, ErrorCode.SYS_NO_USER, "用户不存在");
			    logger.warn("用户名登陆失败！userLoginVo:{}",userLoginVo);
				return rb;
			}
			
			rb.setData(userBean);
		} catch (Exception e) {
			logger.error("用户登陆异常:UserName:{} ,Message>>>{}",
					userLoginVo.getUserName(), e.getMessage());
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean logout(HttpServletRequest request, HttpServletResponse response,
			UserLoginVo userLoginVo) {
		ResultBean rb = new ResultBean();
		try {
			int result = userLoginService.logout(userLoginVo);
			if (result <= 0) {
				rb = new ResultBean(false, ErrorCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			logger.error("用户登陆异常:UserName:{} ,Message>>>{}",
					userLoginVo.getUserName(), e.getMessage());
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}
}
