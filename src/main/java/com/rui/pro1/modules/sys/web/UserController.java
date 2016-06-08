package com.rui.pro1.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.annotatiions.PermissionAnnot;
import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.common.constants.menu.SysMenu;
import com.rui.pro1.common.constants.uri.SysUri;
import com.rui.pro1.common.exception.MessageCode;
import com.rui.pro1.modules.sys.bean.UserBean;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.exception.UserExistException;
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
@RequestMapping(SysUri.SYS_USER)
@MenuAnnot(id = SysMenu.SYS_USER, name = "用户管理", parentId = Modules.SYS, href = "/views/modules/sys/user/userlist",sortNo=1)
public class UserController extends SysBaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserService userService;

	//@RequiresPermissions("chauffeur:carChauffeurRiskDetail:view")

	@PermissionAnnot(id =  SysMenu.SYS_USER + ":list",name="查询列表")
	@RequestMapping(value = {"list",""}, method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getUserList(
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "15") Integer pagesize,
			UserVo user) {
		ResultBean rb = new ResultBean();
		try {
			QueryResult<UserBean> result = userService.getUserList(page, pagesize,
					user);
			rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;

	}
	
	
	@PermissionAnnot(id =  SysMenu.SYS_USER + ":get", name = "查看详情")
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean get(HttpServletRequest request, HttpServletResponse response,
			UserVo userVo) {
		ResultBean rb = new ResultBean();
		try {
			UserBean user = userService.get(userVo.getId());
			rb.setData(user);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@PermissionAnnot(id =  SysMenu.SYS_USER + ":del", name = "删除")
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean del(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "id") Integer id) {
		ResultBean rb = new ResultBean();
		try {
			int count = userService.del(id);
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}
	@PermissionAnnot(id =  SysMenu.SYS_USER + ":add", name = "添加")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean add(HttpServletRequest request, HttpServletResponse response, User user,String repeatPassword) {
		ResultBean rb = new ResultBean();
		try {
			
			if(!StringUtils.isBlank(repeatPassword)){
				if(!repeatPassword.equals(user.getPassword()))
				{
					rb = new ResultBean(false, MessageCode.USER_REPEAT_PASSWORD_ERROR, "密码不一致");
					return rb;
				}
			}
			
			user.setCreateById(userUtils.getUser().getId());
			int count = userService.add(user);
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		} catch (UserExistException e) {
			rb = new ResultBean(false, MessageCode.USER_EXISTS, "用户已存在");
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	//@RequiresPermissions(SysMenu.SYS_USER + ":update")
	@PermissionAnnot(id =  SysMenu.SYS_USER + ":update", name = "修改")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpServletRequest request, HttpServletResponse response,
			User user,String repeatPassword) {
		ResultBean rb = new ResultBean();
		
		try {
			if(StringUtils.isNotBlank(repeatPassword)||StringUtils.isNotBlank(user.getPassword())){
				if(!repeatPassword.equals(user.getPassword()))
				{
					rb = new ResultBean(false, MessageCode.USER_REPEAT_PASSWORD_ERROR, "密码不一致");
					return rb;
				}else{
					//如果需要修改密码，
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.USER_REPEAT_PASSWORD_ERROR, "密码不一致");
			return rb;
		}
		
		try {
			
			user.setUpdateById(userUtils.getUser().getId());

			int count = userService.update(user);
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

}
