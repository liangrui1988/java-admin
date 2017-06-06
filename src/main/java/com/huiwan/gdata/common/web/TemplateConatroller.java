package com.huiwan.gdata.common.web;

import org.springframework.web.bind.annotation.RequestParam;

import com.huiwan.gdata.common.bean.ResultBean;
import com.huiwan.gdata.common.exception.MessageCode;
import com.huiwan.gdata.modules.sys.vo.UserVo;

/**
 * 
 * @author ruiliang
 * @date 2016/04/05
 */
// @RequestMapping(SysUri.SYS_USER)
// @MenuAnnot(id = SysMenu.SYS_USER, name = "菜单名字", parentId = Modules.SYS, href
// = "/views/modules/sys/user/userlist",sortNo=1)
public class TemplateConatroller {
	// @PermissionAnnot(id = SysMenu.SYS_USER + ":list",name="查询列表")
	// @RequestMapping(value = {"list",""}, method = RequestMethod.GET)
	// @ResponseBody
	public ResultBean getUserList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "page", defaultValue = "20") Integer pagesize,
			UserVo user) {
		ResultBean rb = new ResultBean();
		try {
			// QueryResult<User> result = iUserService.getUserList(page,
			// pagesize,
			// user);
			// rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;

	}

}
