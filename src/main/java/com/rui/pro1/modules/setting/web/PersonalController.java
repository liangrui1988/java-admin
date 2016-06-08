package com.rui.pro1.modules.setting.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.annotatiions.PermissionAnnot;
import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.common.constants.menu.SettingMenu;
import com.rui.pro1.common.constants.uri.SettingUri;
import com.rui.pro1.common.exception.MessageCode;
import com.rui.pro1.common.utils.copyo.BeanCopierUtils;
import com.rui.pro1.modules.setting.service.IPersonalService;
import com.rui.pro1.modules.setting.vo.PersonalVo;
import com.rui.pro1.modules.sys.bean.UserBean;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.utils.PassUtil;

@Controller
@RequestMapping(SettingUri.SETTING_PERSONAL)
@MenuAnnot(id = SettingMenu.SETTING_PERSONAL, name = "个人信息", parentId = Modules.SETTING, href = "/views/modules/setting/personal/personalUpdate",sortNo=1)
public class PersonalController extends SettingBaseController {

	@Autowired
	private IPersonalService personalService;
	
	@RequiresPermissions(SettingMenu.SETTING_PERSONAL + ":get")
	@PermissionAnnot(id = SettingMenu.SETTING_PERSONAL + ":get", name = "查看编辑")
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean get(HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			User user=userUtils.getUser();
			UserBean userBean=new UserBean();
			BeanCopierUtils.copyProperties(user, userBean);
			//UserBean user = userService.get(userVo.getId());
			rb.setData(userBean);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}
	
	
	@RequiresPermissions(SettingMenu.SETTING_PERSONAL + ":update")
	@PermissionAnnot(id =  SettingMenu.SETTING_PERSONAL + ":update", name = "修改个人信息")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpServletRequest request, HttpServletResponse response,
			PersonalVo personalVo,String repeatPassword) {
		ResultBean rb = new ResultBean();
	
	
		try {
			personalVo.setPassword(null);
			User user=new User();
			BeanCopierUtils.copyProperties(personalVo, user);
			int count = personalService.update(user);
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}
	
	
	
	@PermissionAnnot(id =  SettingMenu.SETTING_PERSONAL + ":updatePassword", name = "修改密码")
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean updatePassword(HttpServletRequest request, HttpServletResponse response,
			String password,String repeatPassword,String srcPassword) {
		ResultBean rb = new ResultBean();
		
		
		User user = userUtils.getUser();

		//原始密码验证
		try {
			if (StringUtils.isBlank(srcPassword)) {
				rb = new ResultBean(false, MessageCode.USER_SRC_PASSWORD_ERROR,
						"原始密码错误");
				return rb;
			}

			String srcPassowrdEncrypt = PassUtil.encryptPassword(
					user.getUserName(), srcPassword);

			if (!user.getPassword().equals(srcPassowrdEncrypt)) {
				rb = new ResultBean(false, MessageCode.USER_SRC_PASSWORD_ERROR,
						"原始密码错误");
				return rb;
			}

		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.USER_SRC_PASSWORD_ERROR,
					"原始密码错误");
		}
		
		
		//密码是否一致
		try {
			
			if(StringUtils.isNotBlank(repeatPassword)||StringUtils.isNotBlank(password)){
				if(!repeatPassword.equals(password))
				{
					rb = new ResultBean(false, MessageCode.USER_REPEAT_PASSWORD_ERROR, "密码不一致");
					return rb;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.USER_REPEAT_PASSWORD_ERROR, "密码不一致");
			return rb;
		}
		
		try {
			int count = personalService.updatePassword(password,user.getId(),user.getUserName());
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	
	//@RequestMapping(value = "getCurrentUser", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getCurrentUser(HttpServletRequest request, HttpServletResponse response) {
		ResultBean rb = new ResultBean();
		try {
			//UserBean user = userService.get(userVo.getId());
			//rb.setData(user);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}
	
}
