package com.huiwan.gdata.modules.sys.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.annotatiions.PermissionAnnot;
import com.huiwan.gdata.common.bean.ResultBean;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.common.constants.Modules;
import com.huiwan.gdata.common.constants.uri.SysUri;
import com.huiwan.gdata.common.exception.MessageCode;
import com.huiwan.gdata.modules.sys.constants.enums.MenuStatusEnum;
import com.huiwan.gdata.modules.sys.entity.Menu;
import com.huiwan.gdata.modules.sys.service.IMenuService;
import com.huiwan.gdata.modules.sys.service.IUserService;
import com.huiwan.gdata.modules.sys.utils.MenuComparator;
import com.huiwan.gdata.modules.sys.utils.UserUtils;
import com.huiwan.gdata.modules.sys.vo.MenuVo;

/**
 * 菜单管理
 * 
 * @author ruiliang
 * @date 2016/04/05
 *
 */
@Controller
@RequestMapping(SysUri.SYS_MENU)
@MenuAnnot(id = "sys:menu", name = "菜单管理", parentId = Modules.SYS, href = "/views/modules/sys/menu/menulist", sortNo = 3, status = MenuStatusEnum.NORMAL_0)
public class MenuController extends SysBaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IMenuService menuService;

	@Autowired
	private IUserService userService;

	@Autowired
	private UserUtils userUtils;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getList(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pagesize", defaultValue = "12") Integer pagesize, MenuVo menuVo) {
		ResultBean rb = new ResultBean();
		QueryResult<Menu> result = menuService.getMenuList(pageIndex, pagesize, menuVo);
		rb.setData(result);
		return rb;
	}

	@RequestMapping(value = "listAll")
	@ResponseBody
	public ResultBean getlistAll(String level) {
		ResultBean rb = new ResultBean();
		List<Menu> menus = menuService.getMenuListAll();
		// List<Menu> menus = menuService.getMenuListAll(level);
		// Menu[] ocAr = new Menu[menus.size()];
		// for (int i = 0; i < menus.size(); i++) {
		// ocAr[i] = menus.get(i);
		// }
		// Arrays.sort(ocAr, new MenuComparator());
		// List<Menu> menusNew = Arrays.asList(ocAr);
		rb.setData(menus);
		return rb;

	}

	@RequestMapping(value = "getlistByCurentUser")
	@ResponseBody
	public ResultBean getlistByCurentUser() {
		ResultBean rb = new ResultBean();
		String userName = userUtils.getCurrentName();
		List<Menu> menus = userService.getUserMenus(userName);
		if (menus == null || menus.size() <= 0) {
			return rb;
		}
		Set<Menu> menusSet = new HashSet<Menu>();
		menusSet.addAll(menus);
		Menu[] ocAr = new Menu[menusSet.size()];
		menusSet.toArray(ocAr);
		// Menu[] ocAr = new Menu[menusSet.size()];
		// for (int i = 0; i < menusSet.size(); i++) {
		// ocAr[i] = menus.get(i);
		// }
		Arrays.sort(ocAr, new MenuComparator());
		List<Menu> menusNew = Arrays.asList(ocAr);
		// 菜单归类
		List<Menu> result = handlerMenuClassify(menusNew);
		rb.setData(result);
		// String str = JsonUtils.toJsonString(rb);
		// System.out.println("getlistByCurentUser>json>>>:" + str);
		return rb;

	}

	/**
	 * 菜单归类
	 * 
	 * @param menusNew
	 * @return
	 */
	public List<Menu> handlerMenuClassify(List<Menu> menusNew) {
		if (menusNew == null || menusNew.size() <= 0) {
			return menusNew;
		}
		List<Menu> result = new ArrayList<>();// 1级菜单
		Map<String, Menu> map_2 = new LinkedHashMap<>();// 2,3级,id=v
		// 扫描一级
		for (Menu m : menusNew) {
			if (StringUtils.isBlank(m.getParentId())) {
				result.add(m);
				continue;
			}
			// 子级
			map_2.put(m.getId(), m);
		}
		// -------------------------回转-------------------------
		// 二级转list
		for (Menu m_2 : result) {
			for (Entry<String, Menu> entry : map_2.entrySet()) {
				String pid = entry.getValue().getParentId();
				if (m_2.getId().equals(pid)) {// 寻找子
					if (m_2.getMenus() == null || m_2.getMenus().size() <= 0) {// 第一次，先实例
						m_2.setMenus(new ArrayList<Menu>());
					}
					// 加放孩子
					m_2.getMenus().add(entry.getValue());
				}
			}
		}
		// 三级转list
		for (Menu m : result) {
			if (m.getMenus() == null || m.getMenus().size() <= 0) {
				continue;
			}
			// 二级
			for (Menu m_3 : m.getMenus()) {
				for (Entry<String, Menu> entry : map_2.entrySet()) {
					String pid = entry.getValue().getParentId();
					if (m_3.getId().equals(pid)) {// 寻找子
						if (m_3.getMenus() == null || m_3.getMenus().size() <= 0) {// 第一次，先实例
							m_3.setMenus(new ArrayList<Menu>());
						}
						// 加放孩子
						m_3.getMenus().add(entry.getValue());
						// list.add(entry.getValue());
					}
				}
			}
		}
		// 四级转list
		for (Menu m : result) {
			if (m.getMenus() == null || m.getMenus().size() <= 0) {
				continue;
			}
			// 二级
			for (Menu m_3 : m.getMenus()) {
				if (m_3.getMenus() == null || m_3.getMenus().size() <= 0) {
					continue;
				}
				for (Menu m_4 : m_3.getMenus()) {
					for (Entry<String, Menu> entry : map_2.entrySet()) {
						String pid = entry.getValue().getParentId();
						if (m_4.getId().equals(pid)) {// 寻找子
							if (m_4.getMenus() == null || m_4.getMenus().size() <= 0) {// 第一次，先实例
								m_4.setMenus(new ArrayList<Menu>());
							}
							// 加放孩子
							m_4.getMenus().add(entry.getValue());
						}
					}
				}

			}
		}
		return result;
	}

	@PermissionAnnot(id = "sys:menu:get", name = "查询")
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean get(HttpServletRequest request, HttpServletResponse response, MenuVo menuVo) {
		ResultBean rb = new ResultBean();
		Menu menu = menuService.get(Integer.valueOf(menuVo.getId()));
		rb.setData(menu);
		return rb;
	}

	@PermissionAnnot(id = "sys:menu:del", name = "删除")
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean del(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "id") Integer id) {
		ResultBean rb = new ResultBean();
		int count = menuService.del(id);
		if (count <= 0) {
			rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
		}
		return rb;
	}

	@PermissionAnnot(id = "sys:menu:add", name = "添加")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean add(HttpServletRequest request, HttpServletResponse response, Menu role) {
		ResultBean rb = new ResultBean();
		menuService.add(role);
		return rb;
	}

	@PermissionAnnot(id = "sys:menu:update", name = "修改")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpServletRequest request, HttpServletResponse response, Menu menu) {
		ResultBean rb = new ResultBean();
		menuService.update(menu);
		return rb;
	}

}
