package com.huiwan.gdata.modules.sys.init;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.annotatiions.MenuAnnots;
import com.huiwan.gdata.common.annotatiions.PermissionAnnot;
import com.huiwan.gdata.common.constants.enums.MenuReadWrite;
import com.huiwan.gdata.common.utils.dir.Directory.TreeInfo;
import com.huiwan.gdata.modules.pro.web.ProBaseController;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.constants.MenuStatus;
import com.huiwan.gdata.modules.sys.entity.Department;
import com.huiwan.gdata.modules.sys.entity.Menu;
import com.huiwan.gdata.modules.sys.entity.Role;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.mapper.SystemMapper;
import com.huiwan.gdata.modules.sys.service.IDepartmentService;
import com.huiwan.gdata.modules.sys.service.IMenuService;
import com.huiwan.gdata.modules.sys.service.IRoleService;
import com.huiwan.gdata.modules.sys.service.IUserService;

/**
 * 初始化菜单 权限 用户 角色
 * 
 * @author liangrui
 * @date 2016-
 */

@Component
public class InitMP {

	protected Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 初始等级
	 */
	@Value("${initLevel}")
	private String initLevel = "0";

	@Autowired
	private IMenuService menuService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private SystemMapper systemMapper;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserService userService;

	public static void main(String[] args) {
		Annotation[] a = ProBaseController.class.getAnnotations();

		for (Annotation b : a) {
			System.out.println(b);
		}
		System.out.println(a);
	}

	// -------------------------------------------------------------------------------
	// 系统初始化 initLevel=0 执行全部操作 删除用户 角色。。。
	// initLevel=1 执行 删除菜单 重新扫描
	// initLevel=2 执行 删除菜单 重新扫描 并初始admin用户权限
	// esle 什么都不做
	// -------------------------------------------------------------------------------
	@PostConstruct
	public void init() throws Exception {
		log.info("init>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>initLevel={}", initLevel);
		if (initLevel.equals("0")) {
			systemMapper.truncateAllRBAC();
			Set<Menu> menuList = initMenus();
			log.info("xxxxxxxxx==== initLevel=0,初始化用户admin xxxxxxxxxx");
			initUser(menuList, initLevel);
			log.info("xxxxxxxxx==== 初始化用户admin 结束 xxxxxxxxxx");
		} else if (initLevel.equals("1")) {
			log.info("xxxxxxxxx==== initLevel=1,初始化菜单 xxxxxxxxxx");
			systemMapper.truncateMenus();
			initMenus();
		} else if (initLevel.equals("2")) {
			log.info("xxxxxxxxx==== initLevel=2,初始化菜单 &&初始admin xxxxxxxxxx");
			systemMapper.truncateMenus();
			Set<Menu> menuList = initMenus();
			initUser(menuList, initLevel);
			log.info("xxxxxxxxx==== initLevel=2,初始化菜单 &&初始admin 结束 xxxxxxxxxx");
		} else {
		}

	}

	/**
	 * 等级为0执行全部
	 */
	public Set<Menu> initMenus() {
		log.info("xxxxxxxxx==== 扫描菜单 权限 开始xxxxxxxxxx");
		List<String> clzList = new ArrayList<String>();
		// 获取需扫描的一级目录
		// String baseControllerDir = BaseController.class.getProtectionDomain()
		// .getCodeSource().getLocation().getPath();
		// System.out.println("baseControllerDir:" + baseControllerDir);
		// String
		// pa=BaseController.class.getClass().getResource("/").getPath().substring(1);
		// 获取baseController 的包名
		// String basePackName = BaseController.class.getPackage().getName();
		// int endIndex = baseControllerDir.indexOf("controller");
		// 得到需要扫描的路径
		// String rootDir = baseControllerDir.substring(1, endIndex + 10);
		// log.info("======= 扫描包:========>" + rootDir);
		String rootDir = System.getProperty("user.dir");
		log.info("rootDir:{}", rootDir);
		// 开始扫描
		TreeInfo info = TreeInfo.walk(rootDir, ".*.Controller.*.class");// .*.a.*.jpg
		log.info("=======files.size:========>" + info.files.size());
		log.info("=======info.dirs.size():========>" + info.dirs.size());
		for (File f : info.files) {
			String path = f.getPath();
			int start = path.indexOf("com");
			String clzName = path.substring(start, path.length() - 6);
			clzName = clzName.replace("\\", ".");
			clzName = clzName.replace("/", ".");
			// String clzName = f.getName().substring(0, f.getName().length() -
			// 6);
			// basePackName + "."
			log.info("=======扫描到cotroller:========>" + clzName);
			clzList.add(clzName);
		}
		Set<Menu> menuList = new HashSet<Menu>();
		getAnnot(menuList, clzList);
		saveDb(menuList);
		return menuList;

	}

	/**
	 * 获取注解信息
	 * 
	 * @param menuList
	 * @param permissionList
	 */
	public void getAnnot(Set<Menu> menuList, List<String> clzs) {
		if (clzs == null || clzs.size() < 1) {
			log.info("xxxxxxxxx==== 系统没有扫描到菜单 权限 xxxxxxxxxx");
			// System.exit(0);
			return;
		}
		try {
			for (String className : clzs) {
				Class<?> clzz;
				clzz = Class.forName(className);
				// 获取注解数组
				MenuAnnots menuAnnots = clzz.getAnnotation(MenuAnnots.class);// 获取注解
				if (menuAnnots != null) {
					MenuAnnot[] menus = menuAnnots.value();
					if (menus != null) {
						for (MenuAnnot menu : menus) {
							Menu menuEntity = new Menu();
							menuEntity.setName(menu.name());
							menuEntity.setId(menu.id());
							menuEntity.setParentId(menu.parentId());
							menuEntity.setHref(menu.href());
							menuEntity.setSortNo(menu.sortNo());
							menuEntity.setStatus(menu.status().getCode());
							menuList.add(menuEntity);
						}
					}
				}

				MenuAnnot menu = clzz.getAnnotation(MenuAnnot.class);// 获取注解
				if (menu == null) {
					continue;
				}
				Menu menuEntity = new Menu();
				menuEntity.setName(menu.name());
				menuEntity.setId(menu.id());
				menuEntity.setParentId(menu.parentId());
				menuEntity.setHref(menu.href());
				menuEntity.setSortNo(menu.sortNo());
				// menuEntity.setc(new Date());
				// menuEntity.setCreater("系统扫描");
				// menuEntity.setClient(menu.client());
				// menuEntity.setStatus(MenuStatus.NORMAL_0);
				menuEntity.setStatus(menu.status().getCode());
				menuList.add(menuEntity);
				// System.out.println("menuEntity:" + menuEntity);
				// 获取方法
				Method[] methods = clzz.getMethods();
				for (Method me : methods) {
					PermissionAnnot permission = me.getAnnotation(PermissionAnnot.class);
					if (permission == null) {
						continue;
					}
					// 如果是只读防问控制 就不写入了
					if (permission.readWrite().getValue() == MenuReadWrite.Read.getValue()) {
						continue;
					}
					Menu menuthree = new Menu();
					menuthree.setName(permission.name());
					menuthree.setId(permission.id());
					menuthree.setParentId(menu.id());
					// menuthree.setHref(menu.href());
					// FIXME:href
					menuthree.setSortNo(menu.sortNo());
					menuthree.setStatus(MenuStatus.PERMISSION_1);// 不显示
					menuList.add(menuthree);

				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	// 保存 List<Menu> menuList, List<Permission> permissionList
	public void saveDb(Set<Menu> entityList) {
		for (Menu menu : entityList) {
			int count = menuService.add(menu);
			if (count <= 0) {
				log.error("菜单没有初始成功！程序退出。。。。。。。");
				System.exit(0);
			}
		}

	}

	// 初始用户
	public void initUser(Set<Menu> menus, String initLevel) throws Exception {
		// 部门
		Department department = new Department();
		department.setName("技术部");
		department.setSort(1);
		department.setParentId(0);
		department.setRemake("remake");
		if (initLevel.equals("0")) {
			departmentService.add(department);
		} else if (initLevel.equals("2")) {

		}

		// 分置菜单
		// 角色
		Role role = new Role();
		role.setName("系统管理");
		role.setRemake("remake");
		role.setCreateById(1);
		role.setTypes("1");

		List<String> menuIds = new ArrayList<String>();
		for (Menu m : menus) {
			menuIds.add(m.getId());
		}
		role.setMenuIds(menuIds);
		if (initLevel.equals("0")) {
			roleService.add(role);

		} else if (initLevel.equals("2")) {
			Role roleupdate = roleService.getByName("系统管理");
			if (roleupdate == null || roleupdate.getId() == null || roleupdate.getId() <= 0) {
				roleService.add(role);
			} else {
				role.setId(roleupdate.getId());
				roleService.update(role);
			}

		}

		if (initLevel.equals("0")) {
			Role roleB = new Role();
			roleB.setName("商家角色");
			roleB.setRemake("remake");
			// roleB.setMenuIds(menuIds);
			roleService.add(roleB);
		} else if (initLevel.equals("2")) {

		}

		// 用户
		User user = new User();
		user.setUserName("admin");
		user.setPassword("admin");
		user.setFullName("林冲");
		user.setEmail("1067165280@qq.com");
		user.setCreateTime(new Date());
		user.setType("1");
		user.setRemake("系统初始用户");
		user.setCreateById(1);
		if (initLevel.equals("0")) {
			// 关联角色
			List<Role> roles = new ArrayList<Role>();
			Role role2 = new Role();
			role2.setId(role.getId());
			roles.add(role2);
			user.setRoles(roles);
			// 部门
			user.setDepartmentId(department.getId());
			userService.add(user);
		} else {
			// 所有角色和部门
			List<Role> rolesAll = roleService.getRoleListAll();
			user.setRoles(rolesAll);
			UserBean dbUser = userService.getUser("admin");
			if (dbUser == null || dbUser.getId() == null || dbUser.getId() <= 0) {
				userService.add(user);
			} else {
				user.setId(dbUser.getId());
				userService.update(user);
			}

		}
		System.out.println("userId>>>" + user.getId());

	}

}
