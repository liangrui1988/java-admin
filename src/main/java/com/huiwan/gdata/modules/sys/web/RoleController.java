package com.huiwan.gdata.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.annotatiions.PermissionAnnot;
import com.huiwan.gdata.common.bean.ResultBean;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.common.constants.Modules;
import com.huiwan.gdata.common.constants.uri.SysUri;
import com.huiwan.gdata.common.exception.MessageCode;
import com.huiwan.gdata.modules.sys.bean.RoleBean;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.entity.Role;
import com.huiwan.gdata.modules.sys.entity.RoleGroup;
import com.huiwan.gdata.modules.sys.exception.ObjectExistException;
import com.huiwan.gdata.modules.sys.service.IRoleService;
import com.huiwan.gdata.modules.sys.utils.UserUtils;
import com.huiwan.gdata.modules.sys.vo.RoleVo;

/**
 * 用户管理
 * 
 * @author ruiliang
 * @date 2016/04/05
 *
 */
@Controller
@RequestMapping(SysUri.SYS_ROLE)
@MenuAnnot(id = "sys:role", name = "角色管理", parentId = Modules.SYS, href = "/views/modules/sys/role/rolelist", sortNo = 2)
public class RoleController extends SysBaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IRoleService roleService;

	@PermissionAnnot(id = "sys:role:list", name = "查看列表")
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getList(@RequestParam(value = "pageIndex", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "15") Integer pagesize, RoleVo roleVo) {
		ResultBean rb = new ResultBean();
		QueryResult<Role> result = roleService.getRoleList(page, pagesize, roleVo);
		rb.setData(result);
		return rb;

	}

	@RequestMapping(value = "indexRoleMark", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getIndexRoleMark(RoleVo roleVo) {
		ResultBean rb = new ResultBean();
		//当前用户拥有的角色条件 ,暂略...
//		roleVo.setUserId(userUtils.getUserBean().getId());
		QueryResult<Role> result = roleService.getRoleList(1, 20, roleVo);
		// 如果没有会话角色标识
		if (userUtils.getSession().getAttribute(UserUtils.ROLE_MARK) == null) {
			List<Role> list = result.getItems();
			if (list != null && list.size() > 0) {
				Role roleinfo = list.get(0);
				// 初始角色类型-标识
				userUtils.getSession().setAttribute(UserUtils.ROLE_MARK, roleinfo.getTypes());
			}
		}
		// 获取当前
		Object rolemark = userUtils.getSession().getAttribute(UserUtils.ROLE_MARK);
		if (rolemark != null) {
			rb.setMessage(rolemark.toString());
		}
		rb.setData(result);
		return rb;
	}

	@RequestMapping(value = "indexUpdateRoleMark")
	@ResponseBody
	public ResultBean indexUpdateRoleMark(String roleType) {
		ResultBean rb = new ResultBean();
		// 验证 略...
		// RoleVo roleVo=new RoleVo();
		// roleVo.setGroupId(2);
		// QueryResult<Role> result = roleService.getRoleList(1, 20,
		// roleVo);
		// List<Role> list=result.getItems();
		// 改变角色类型-标识
		userUtils.getSession().setAttribute(UserUtils.ROLE_MARK, roleType);
		return rb;
	}

	@RequestMapping(value = "getRoleMark")
	@ResponseBody
	public ResultBean getRoleMark() {
		ResultBean rb = new ResultBean();
		rb.setData(userUtils.getCurrentMark());
		return rb;
	}

	// @PermissionAnnot(id = MenuSys.SYS_ROLE + ":list", name = "查看列表")
	@RequestMapping(value = "listAll", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getListAll() {
		ResultBean rb = new ResultBean();
		List<Role> result = roleService.getRoleListAll();
		rb.setData(result);
		return rb;
	}

	@RequestMapping(value = "listGroupAll", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean listGroupAll() {
		ResultBean rb = new ResultBean();
		List<RoleGroup> result = roleService.getGroupListAll();
		rb.setData(result);
		return rb;
	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	@PermissionAnnot(id = "sys:role:get", name = "查看")
	@ResponseBody
	public ResultBean get(HttpServletRequest request, HttpServletResponse response, RoleVo roleVo) {
		ResultBean rb = new ResultBean();
		RoleBean role = roleService.get(roleVo.getId());
		rb.setData(role);
		return rb;
	}

	@PermissionAnnot(id = "sys:role:del", name = "删除")
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean del(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "id") Integer id) {
		ResultBean rb = new ResultBean();
		int count = roleService.del(id);
		if (count <= 0) {
			rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
		}
		return rb;
	}

	@PermissionAnnot(id = "sys:role:add", name = "添加")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean add(HttpServletRequest request, HttpServletResponse response, Role role) {
		ResultBean rb = new ResultBean();
		try {
			UserBean user = userUtils.getUserBean();
			role.setUpdateById(user.getId());
			roleService.add(role);
		} catch (ObjectExistException e) {
			rb = new ResultBean(false, MessageCode.ROLE_EXISTS, "角色已存在");
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}

		return rb;
	}

	@PermissionAnnot(id = "sys:role:update", name = "修改")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpServletRequest request, HttpServletResponse response, Role role) {
		ResultBean rb = new ResultBean();
		roleService.update(role);
		return rb;
	}

}
