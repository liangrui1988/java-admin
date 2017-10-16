package com.huiwan.gdata.modules.setting.web;

import org.springframework.stereotype.Controller;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.constants.Modules;
import com.huiwan.gdata.modules.BaseController;
@Controller
@MenuAnnot(id = Modules.SETTING, name = "基本设置", parentId = "",href = "",sortNo=4)
public class SettingBaseController  extends BaseController{

}
