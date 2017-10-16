package com.huiwan.gdata.modules.gdata.zl.web;

import org.springframework.stereotype.Controller;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.annotatiions.MenuAnnots;
import com.huiwan.gdata.modules.BaseController;

@Controller
@MenuAnnots({ @MenuAnnot(id = "zl", name = "战卢特性数据", parentId = "", href = "", sortNo =6),
		@MenuAnnot(id = "zl:jnfx", name = "技能分析", parentId = "zl", href = "", sortNo = 1),
		@MenuAnnot(id = "zl:jnfx:jnpm", name = "技能排名", parentId = "zl:jnfx", href = "", sortNo = 1),
		})
public class ZlBaseController extends BaseController {


}
