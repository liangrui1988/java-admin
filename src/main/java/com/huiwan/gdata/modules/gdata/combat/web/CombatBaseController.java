package com.huiwan.gdata.modules.gdata.combat.web;

import org.springframework.stereotype.Controller;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.constants.Modules;

@Controller
@MenuAnnot(id = Modules.COMBAT, name = "战斗分析", parentId = "",href = "",sortNo=10)
public class CombatBaseController {

	public CombatBaseController() {
		// TODO Auto-generated constructor stub
	}

}
