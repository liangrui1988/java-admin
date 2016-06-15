package com.rui.pro1.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.rui.pro1.modules.sys.entity.Dict;
import com.rui.pro1.modules.sys.exception.ObjectExistException;
import com.rui.pro1.modules.sys.exception.UserExistException;
import com.rui.pro1.modules.sys.service.IDictService;

@Controller
@RequestMapping(SysUri.SYS_DICT)
@MenuAnnot(id = SysMenu.SYS_DICT, name = "字典管理", parentId = Modules.SYS, href = "/views/modules/sys/dict/dictlist", sortNo = 5)
public class DictController extends SysBaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IDictService dictService;

	// @RequiresPermissions("chauffeur:carChauffeurRiskDetail:view")

	@PermissionAnnot(id = SysMenu.SYS_DICT + ":list", name = "查询列表")
	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getList(
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "15") Integer pagesize,
			Dict dict) {
		ResultBean rb = new ResultBean();
		try {
			QueryResult<Dict> result = dictService.getList(page, pagesize, dict);
			rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;

	}

	@PermissionAnnot(id = SysMenu.SYS_DICT + ":get", name = "查看详情")
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean get(HttpServletRequest request,
			HttpServletResponse response, Dict dict) {
		ResultBean rb = new ResultBean();
		try {
			Dict result = dictService.get(dict.getId());
			rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}
	
	
//	@PermissionAnnot(id = SysMenu.SYS_DICT + ":get", name = "查看详情")
	@RequestMapping(value = "getByType", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getByType(HttpServletRequest request,
			HttpServletResponse response, String type) {
		ResultBean rb = new ResultBean();
		
		if(StringUtils.isBlank(type)){
			return rb;
		}
		
		try {
			dictService.getByType(type);
			List<Dict> result = dictService.getByType(type);
			rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@PermissionAnnot(id = SysMenu.SYS_DICT + ":del", name = "删除")
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean del(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false, value = "id") Integer id) {
		ResultBean rb = new ResultBean();
		try {
			int count = dictService.del(id);
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@PermissionAnnot(id = SysMenu.SYS_DICT + ":add", name = "添加")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean add(HttpServletRequest request,
			HttpServletResponse response, Dict dict) {
		ResultBean rb = new ResultBean();
		try {
			dict.setCreateById(userUtils.getUser().getId());
			int count = dictService.add(dict);
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		} catch (ObjectExistException e) {
			rb = new ResultBean(false, MessageCode.OBJECT_EXIST_EXCEPTION, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@PermissionAnnot(id = SysMenu.SYS_DICT + ":update", name = "修改")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpServletRequest request,
			HttpServletResponse response, Dict dict) {
		ResultBean rb = new ResultBean();
		try {
			dict.setUpdateById(userUtils.getUser().getId());
			int count = dictService.update(dict);
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		} catch (ObjectExistException e) {
			rb = new ResultBean(false, MessageCode.OBJECT_EXIST_EXCEPTION, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}
}
