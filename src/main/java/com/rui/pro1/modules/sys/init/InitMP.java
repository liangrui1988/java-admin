package com.rui.pro1.modules.sys.init;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.annotatiions.PermissionAnnot;
import com.rui.pro1.common.utils.dir.Directory.TreeInfo;
import com.rui.pro1.modules.sys.entity.Department;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.entity.Role;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.mapper.SystemMapper;
import com.rui.pro1.modules.sys.service.IDepartmentService;
import com.rui.pro1.modules.sys.service.IMenuService;
import com.rui.pro1.modules.sys.service.IRoleService;
import com.rui.pro1.modules.sys.service.IUserService;
import com.rui.pro1.modules.sys.utils.PassUtil;

/**
 * 初始化菜单 权限 用户 角色
 * 
 * @author liangrui
 *
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
	


	// -------------------------------------------------------------------------------
	// 系统初始化 initLevel=0 执行全部操作 删除用户 角色。。。
	// initLevel=1 执行 删除菜单 重新扫描
	// esle 什么都不做
	// -------------------------------------------------------------------------------
	@PostConstruct
	public void init() throws IOException {
		log.info("init>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>initLevel={}",
				initLevel);

		if (initLevel.equals("0")) {
			systemMapper.truncateAllRBAC();

			Set<Menu> menuList = initMenus();
			log.info("xxxxxxxxx==== 初始化用户admin xxxxxxxxxx");
			initUser(menuList);
			log.info("xxxxxxxxx==== 初始化用户admin 结束 xxxxxxxxxx");

		} else if (initLevel.equals("1")) {
			systemMapper.truncateMenus();
			initMenus();
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
//		String baseControllerDir = BaseController.class.getProtectionDomain()
//				.getCodeSource().getLocation().getPath();
//		System.out.println("baseControllerDir:" + baseControllerDir);

	
		//String pa=BaseController.class.getClass().getResource("/").getPath().substring(1);
		
		// 获取baseController 的包名
		//String basePackName = BaseController.class.getPackage().getName();

		//int endIndex = baseControllerDir.indexOf("controller");
		// 得到需要扫描的路径
//		String rootDir = baseControllerDir.substring(1, endIndex + 10);
		//log.info("======= 扫描包:========>" + rootDir);

		
		String rootDir = System.getProperty("user.dir");
		log.info("rootDir:{}" , rootDir);
		
		// 开始扫描
		TreeInfo info = TreeInfo.walk(rootDir, ".*.Controller.*.class");// .*.a.*.jpg

		log.info("=======files.size:========>" + info.files.size());
		log.info("=======info.dirs.size():========>" + info.dirs.size());

		for (File f : info.files) {
			String path=f.getPath();
			int start=path.indexOf("com");
			String clzName = path.substring(start, path.length() - 6);
			clzName=clzName.replace("\\", ".");
			clzName=clzName.replace("/", ".");

			//String clzName = f.getName().substring(0, f.getName().length() - 6);
			//basePackName + "."
			log.info("=======扫描到cotroller:========>"+ clzName);
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
				menuEntity.setStatus(0);
				menuList.add(menuEntity);
				// System.out.println("menuEntity:" + menuEntity);
				// 获取方法
				Method[] methods = clzz.getMethods();

				for (Method me : methods) {

					PermissionAnnot permission = me
							.getAnnotation(PermissionAnnot.class);
					if (permission == null) {
						continue;
					}

					Menu menuthree = new Menu();
					menuthree.setName(permission.name());
					menuthree.setId(permission.id());
					menuthree.setParentId(menu.id());
					// menuthree.setHref(menu.href());
					// FIXME:href
					menuthree.setSortNo(menu.sortNo());
					menuthree.setStatus(1);//不显示
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
	public void initUser(Set<Menu> menus) {

		// 部门
		Department department = new Department();
		department.setName("技术部");
		department.setSort(1);
		department.setParentId(0);
		department.setRemake("remake");
		departmentService.add(department);

		// 分置菜单

		// 角色
		Role role = new Role();
		role.setName("系统管理员");
		role.setRemake("remake");
		List<String> menuIds = new ArrayList<String>();
		for (Menu m : menus) {
			menuIds.add(m.getId());
		}
		role.setMenuIds(menuIds);
		roleService.add(role);
		
		Role roleB = new Role();
		roleB.setName("普通用户");
		roleB.setRemake("普通用户");
//		List<String> menuIds2 = new ArrayList<String>();
//		for (Menu m : menus) {
//			menuIds2.add(m.getId());
//		}
		roleB.setMenuIds(menuIds);

		roleService.add(roleB);

		// 用户
		User user = new User();
		user.setUserName("admin");
		user.setPassword("admin");
//		String password = PassUtil.encryptPassword("admin", "admin");
//		System.out.println(password);
		//user.setPassword(password);
	
		// 关联角色
		List<Role> roles = new ArrayList<Role>();
		Role role2 = new Role();
		role2.setId(role.getId());
		roles.add(role2);
		user.setRoles(roles);
		// 部门
		user.setDepartmentId(department.getId());
		userService.add(user);
		System.out.println("userId>>>" + user.getId());

	}
}
