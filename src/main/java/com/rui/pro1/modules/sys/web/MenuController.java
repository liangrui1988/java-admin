package com.rui.pro1.modules.sys.web;

import java.util.ArrayList;
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

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.annotatiions.PermissionAnnot;
import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.common.constants.menu.MenuSys;
import com.rui.pro1.common.constants.uri.SysUri;
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
@RequestMapping(SysUri.SYS_MENU)
@MenuAnnot(id = MenuSys.SYS_MENU, name = "菜单管理", parentId = Modules.SYS, href = "views/modules/sys/menulist",sortNo=3)
public class MenuController extends SysBaseControoler {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IMenuService menuService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize,
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
	
	
	
	
	@RequestMapping(value = "listAll")
	@ResponseBody
	public ResultBean getlistAll() {
		ResultBean rb = new ResultBean();
		try {
			
			List<MenuVo> mvo=new ArrayList<MenuVo>();
			
			MenuVo menuVo=new MenuVo();
			menuVo.setId("1");
			menuVo.setParentId("");
			menuVo.setName("系统管理");
			mvo.add(menuVo);
			
			MenuVo menuVox =new MenuVo();
			menuVox.setId("10");
			menuVox.setParentId("1");
			menuVox.setName("用户管理 ");
			mvo.add(menuVox);
			
			MenuVo menuVoa  =new MenuVo();
			menuVoa.setId("11");
			menuVoa.setParentId("1");
			menuVoa.setName("菜单管理 ");
			mvo.add(menuVoa);
			
			
			//--------------------------------
			
			MenuVo menuVo2=new MenuVo();
			menuVo2.setId("2");
			menuVo2.setParentId("");
			menuVo2.setName("财务管理");
			mvo.add(menuVo2);
			
			
		
			
			
			MenuVo menuVo3=new MenuVo();
			menuVo3.setId("3");
			menuVo3.setParentId("");
			menuVo3.setName("采购管理");
			mvo.add(menuVo3);
			
			
			rb.setData(mvo);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;

	}
	
	@PermissionAnnot(id =  MenuSys.SYS_MENU + ":get", name = "查询")
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean get(HttpServletRequest request, HttpServletResponse response,
			MenuVo menuVo) {
		ResultBean rb = new ResultBean();
		try {
			Menu menu = menuService.get(Integer.valueOf(menuVo.getId()));
			rb.setData(menu);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@PermissionAnnot(id =  MenuSys.SYS_MENU + ":del", name = "删除")
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
	
	
	@PermissionAnnot(id =  MenuSys.SYS_MENU + ":add", name = "添加")
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

	@PermissionAnnot(id =  MenuSys.SYS_MENU + ":update", name = "修改")
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
