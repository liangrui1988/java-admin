package com.huiwan.gdata.modules.pro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.annotatiions.PermissionAnnot;
import com.huiwan.gdata.common.bean.ResultBean;
import com.huiwan.gdata.common.constants.Modules;
import com.huiwan.gdata.common.constants.uri.ProUri;
import com.huiwan.gdata.common.exception.MessageCode;
import com.huiwan.gdata.modules.sys.vo.UserVo;

//@Controller
//@RequestMapping(ProUri.PRO_MER)
//@MenuAnnot(id = "pro2:mer", name = "商家商品维护", parentId = Modules.PRO2, href = "/views/modules/pro/merproduct/list", sortNo = 1)
public class MerProductController extends ProBaseController {

//	@PermissionAnnot(id = "pro:mer:list",name = "查询列表")
//	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
//	@ResponseBody
	public ResultBean getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize,
			UserVo user) {
		ResultBean rb = new ResultBean();
		try {
			// QueryResult<User> result = userService.getUserList(page,
			// pagesize,
			// user);
			rb.setData("");
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;

	}
}
