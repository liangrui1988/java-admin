package com.huiwan.gdata.modules.sys.web;

import org.springframework.stereotype.Controller;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.constants.Modules;
import com.huiwan.gdata.modules.BaseController;


@Controller
@MenuAnnot(id = Modules.SYS, name = "系统管理", parentId = "",href = "",sortNo=1)
public class SysBaseController extends BaseController {

}
