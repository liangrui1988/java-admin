package com.huiwan.gdata.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.huiwan.gdata.modules.sys.entity.Parameter;
import com.huiwan.gdata.modules.sys.service.IParameterService;
import com.huiwan.gdata.modules.sys.vo.MenuVo;

/**
 * 系统参数
 * 
 * @author rui
 *
 */
@Controller
@RequestMapping(SysUri.SYS_PARAMETER)
@MenuAnnot(id = "sys:parameter", name = "系统参数配置", parentId = Modules.SYS, href = "/views/modules/sys/parameter/parameterlist", sortNo = 6)
public class ParameterController extends SysBaseController {

	@Autowired
	private IParameterService parameterService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getList(
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pagesize", defaultValue = "12") Integer pagesize,
			Parameter parameter) {
		ResultBean rb = new ResultBean();
		QueryResult<Parameter> result = parameterService.getList(pageIndex,
				pagesize, parameter);
		rb.setData(result);

		return rb;
	}

	@PermissionAnnot(id = "sys:parameter:get", name = "查询")
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean get(HttpServletRequest request,
			HttpServletResponse response, MenuVo menuVo) {
		ResultBean rb = new ResultBean();

		Parameter parameter = parameterService.get(Integer.valueOf(menuVo
				.getId()));
		rb.setData(parameter);

		return rb;
	}

	@PermissionAnnot(id = "sys:parameter:del", name = "删除")
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean del(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false, value = "id") Integer id) {
		ResultBean rb = new ResultBean();

		int count = parameterService.del(id);
		if (count <= 0) {
			rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
		}

		return rb;
	}

	@PermissionAnnot(id = "sys:parameter:add", name = "添加")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean add(HttpServletRequest request,
			HttpServletResponse response, Parameter parameter) {
		ResultBean rb = new ResultBean();

		parameterService.add(parameter);

		return rb;
	}

	@PermissionAnnot(id = "sys:parameter:update", name = "修改")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpServletRequest request,
			HttpServletResponse response, Parameter parameter) {
		ResultBean rb = new ResultBean();

		parameterService.update(parameter);

		return rb;
	}
}
