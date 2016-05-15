package com.rui.pro1.modules.sys.web;

import org.springframework.stereotype.Controller;

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.modules.BaseController;


@Controller
@MenuAnnot(id = Modules.SYS, name = "系统管理", parentId = "",href = "",sortNo=1)
public class SysBaseController extends BaseController {

}
