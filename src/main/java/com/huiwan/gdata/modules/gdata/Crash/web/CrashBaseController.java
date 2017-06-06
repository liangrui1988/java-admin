package com.huiwan.gdata.modules.gdata.Crash.web;

import org.springframework.stereotype.Controller;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.constants.Modules;
import com.huiwan.gdata.modules.BaseController;

@Controller
@MenuAnnot(id = Modules.CRASH, name = "异常分析", parentId = "",href = "",sortNo=10)
public class CrashBaseController   extends BaseController{

}
