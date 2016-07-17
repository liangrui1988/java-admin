package com.rui.pro1.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.common.constants.menu.SysMenu;
import com.rui.pro1.common.constants.uri.SysUri;
import com.rui.pro1.modules.sys.service.impl.ParameterService;

/**
 * 系统参数
 * 
 * @author rui
 *
 */
@Controller
@RequestMapping(SysUri.SYS_PARAMETER)
@MenuAnnot(id = SysMenu.SYS_PARAMETER, name = "系统参数配置", parentId = Modules.SYS, href = "/views/modules/sys/parameter/parameterlist",sortNo=6)
public class ParameterController extends SysBaseController {

	@Autowired
	private ParameterService parameterService;
}
