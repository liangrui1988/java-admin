package com.rui.pro1.modules.setting.web;

import org.springframework.stereotype.Controller;

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.modules.BaseController;
@Controller
@MenuAnnot(id = Modules.SETTING, name = "基本设置", parentId = "",href = "",sortNo=4)
public class SettingBaseController  extends BaseController{

}
