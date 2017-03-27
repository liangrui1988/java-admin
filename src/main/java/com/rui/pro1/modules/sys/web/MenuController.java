package com.rui.pro1.modules.sys.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.annotatiions.PermissionAnnot;
import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.common.constants.uri.SysUri;
import com.rui.pro1.common.exception.MessageCode;
import com.rui.pro1.common.utils.json.JsonUtils;
import com.rui.pro1.modules.sys.constants.enums.MenuStatusEnum;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.service.IMenuService;
import com.rui.pro1.modules.sys.service.IUserService;
import com.rui.pro1.modules.sys.utils.MenuComparator;
import com.rui.pro1.modules.sys.utils.UserUtils;
import com.rui.pro1.modules.sys.vo.MenuVo;

import freemarker.template.utility.StringUtil;

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
		if (menusNew == null || menusNew.size() <= 0) {
			rb.setData(menusNew);
			return rb;
		}
		List<Menu> result = new ArrayList<>();// 1级菜单
		List<Menu> result_2 = new ArrayList<>();// 2级菜单
		Map<String, Menu> map_1 = new LinkedHashMap<>();// 1级菜单
		Map<String, Menu> map_2 = new LinkedHashMap<>();// 2,3级,id=v
		Map<String, Menu> map_3 = new LinkedHashMap<>();
		// 扫描一级
		for (Menu m : menusNew) {
			if (StringUtils.isBlank(m.getParentId())) {
				map_1.put(m.getId(), m);
				result.add(m);
				continue;
			}
			// 子级
			map_2.put(m.getId(), m);
		}
		Map<String, List<Menu>> map_list_1 = new LinkedHashMap<>();// pid(1级id)=v
		// 二级处理
		for (Entry<String, Menu> entry : map_2.entrySet()) {
			// 如果一级菜单有子菜单同，则加入
			if (map_1.containsKey(entry.getValue().getParentId())) {
				List<Menu> list_1 = null;
				if (map_list_1.containsKey(entry.getValue().getParentId())) {// 取出列表，再放入
					list_1 = map_list_1.get(entry.getValue().getParentId());
				} else {// 第一次，先实例
					list_1 = new ArrayList<>();
				}
				list_1.add(entry.getValue());
				result_2.add(entry.getValue());// 二级列表
				map_list_1.put(entry.getValue().getParentId(), list_1);
			} else { // 不存在归类到 三级
				map_3.put(entry.getKey(), entry.getValue());
			}
		}
		
		if (map_3.size() <= 0) {
			rb.setData(menusNew);
			return rb;
		}
		// 三级处理,先循环三级菜单，根据父id，找到对应的二级菜单，合并起来
		Map<String, List<Menu>> map_list_2 = new LinkedHashMap<>();// pid(1级id)=v
		for (Entry<String, List<Menu>> entry : map_list_1.entrySet()) {// 一级
			for (Menu m : entry.getValue()) {// 二级
				// if (map_3.containsKey(m.getId())) {
				for (Entry<String, Menu> m_3 : map_3.entrySet()) {// 二级下面下面的孩子id
					if (m_3.getValue().getParentId().equals(m.getId())) {
						List<Menu> list = null;
						if (map_list_2.containsKey(m.getId())) {// 取出列表，再放入
							list = map_list_1.get(m.getId());
						} else {// 第一次，先实例
							list = new ArrayList<>();
						}
						list.add(m_3.getValue());
						map_list_2.put(m.getId(), list);
					}
				}
			}
		}
		// -------------------------回转-------------------------
		// 一级转list
		for (Menu m : result) {
			if (map_list_1.containsKey(m.getId())) {
				// 在二级里面找找出
				List<Menu> subclass = map_list_1.get(m.getId());
				m.setMenus(subclass);
			}
		}

		// 二级转list
		for (Menu m : result) {
			if (m.getMenus() == null || m.getMenus().size() <= 0) {
				continue;
			}
			for (Menu m_2 : m.getMenus()) {
				if (map_list_2.containsKey(m.getId())) {
					// 在二级里面找找出
					List<Menu> subclass = map_list_2.get(m.getId());
					m_2.setMenus(subclass);
				}
			}

		}
		rb.setData(result);
		String str = JsonUtils.toJsonString(rb);
		System.out.println("getlistByCurentUser>json>>>:" + str);
		return rb;

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
