package com.rui.pro1.modules.sys.web;

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

import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.common.exception.ErrorCode;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.service.IMenuService;
import com.rui.pro1.modules.sys.vo.MenuVo;

/**
 * 用户管理
 * 
 * @author ruiliang
 * @date 2016/04/05
 *
 */
@Controller
@RequestMapping("sys/menu")
public class MenuController extends SysBaseControoler {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IMenuService menuService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "page", defaultValue = "20") Integer pagesize,
			MenuVo menuVo) {
		ResultBean rb = new ResultBean();
		try {
			QueryResult<Menu> result = menuService.getMenuList(page, pagesize,
					menuVo);
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
			MenuVo menuVo) {
		ResultBean rb = new ResultBean();
		try {
			Menu menu = menuService.get(menuVo.getId());
			rb.setData(menu);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean del(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "id") Integer id) {
		ResultBean rb = new ResultBean();
		try {
			int count = menuService.del(id);
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
	public ResultBean add(HttpServletRequest request, HttpServletResponse response,Menu role) {
		ResultBean rb = new ResultBean();
		try {
			menuService.add(role);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpServletRequest request, HttpServletResponse response,
			Menu menu) {
		ResultBean rb = new ResultBean();
		try {
			menuService.update(menu);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

}
