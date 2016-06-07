package com.rui.pro1.modules.pro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.annotatiions.PermissionAnnot;
import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.common.constants.menu.ProMenu;
import com.rui.pro1.common.constants.uri.ProUri;
import com.rui.pro1.common.exception.MessageCode;
import com.rui.pro1.modules.sys.vo.UserVo;

@Controller
@RequestMapping(ProUri.PRO_MER)
@MenuAnnot(id = ProMenu.PRO_MER, name = "商家商品维护", parentId = Modules.PRO, href = "/views/modules/pro/merproduct/list",sortNo=1)
public class MerProductController extends ProBaseController {

	
	
	@PermissionAnnot(id =  ProMenu.PRO_MER + ":list")
	@RequestMapping(value = {"list",""}, method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize,
			UserVo user) {
		ResultBean rb = new ResultBean();
		try {
//			QueryResult<User> result = userService.getUserList(page, pagesize,
//					user);
			rb.setData("");
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;

	}
}
