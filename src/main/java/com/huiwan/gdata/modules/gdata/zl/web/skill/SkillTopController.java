package com.huiwan.gdata.modules.gdata.zl.web.skill;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.bean.ResultBean;
import com.huiwan.gdata.common.utils.pagination.Paginator;
import com.huiwan.gdata.modules.gdata.base.charset.bean.QueryCommBean;

@Controller
@RequestMapping("zl_jnfx_jnpm/test")
@MenuAnnot(id = "zl:jnfx:jnpm:test", name = "测试图表", parentId = "zl:jnfx:jnpm", href = "/views/modules/gdata/zl/skill/zl_skill_data", sortNo = 1)
public class SkillTopController {

	
	@RequestMapping(value = { "show", "" }, method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getList(Paginator paginator, QueryCommBean bean) {
		 ResultBean rb = new ResultBean();
		return rb;

	}
	
}
