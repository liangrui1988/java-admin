package com.huiwan.gdata.modules.gdata.xy.web;

import org.springframework.stereotype.Controller;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.annotatiions.MenuAnnots;
import com.huiwan.gdata.modules.BaseController;

@Controller
@MenuAnnots({ @MenuAnnot(id = "xy", name = "轩辕特性数据", parentId = "", href = "", sortNo = 7),
		@MenuAnnot(id = "xydata", name = "二级菜单", parentId = "xy", href = "", sortNo = 1) })
public class XyBaseController extends BaseController {


}
