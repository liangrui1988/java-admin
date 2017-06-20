package com.huiwan.gdata.modules.gdata.combat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.bean.ResultBean;
import com.huiwan.gdata.common.constants.Modules;
import com.huiwan.gdata.common.exception.MessageCode;
import com.huiwan.gdata.common.utils.pagination.Paginator;
import com.huiwan.gdata.common.utils.pagination.PaginatorResult;
import com.huiwan.gdata.modules.gdata.base.charset.bean.QueryCommBean;
import com.huiwan.gdata.modules.gdata.combat.entity.CombatLog;
import com.huiwan.gdata.modules.gdata.combat.service.CombatLogService;
import com.huiwan.gdata.modules.sys.entity.Dict;

@Controller
@RequestMapping("combat/log")
@MenuAnnot(id = "combat:log", name = "战斗日志", parentId = Modules.COMBAT, href = "/views/modules/gdata/combat/combatLog", sortNo = 1)
public class CombatLogController {

	@Autowired
	private CombatLogService combatLogService;

	@RequestMapping(value = { "combatList", "" }, method = RequestMethod.GET)
	@ResponseBody
	public PaginatorResult getList(Paginator paginator, QueryCommBean bean) {
		// ResultBean rb = new ResultBean();
		// try {
		PaginatorResult result = combatLogService.getPaginatorList(paginator, bean);
		// rb.setData(result);
		// } catch (Exception e) {
		// e.printStackTrace();
		// rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		// }
		return result;

	}

	@RequestMapping(value = { "getDetail" }, method = RequestMethod.GET)
	@ResponseBody
	public String getDetail(Integer id) {
		CombatLog result = combatLogService.getDetail(id);
		return result.getCont();
	}

	@RequestMapping(value = { "getUuids" }, method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getUuids() {
		ResultBean rb = new ResultBean();
		try {
			List<Dict> result = combatLogService.getObjTypes();
			rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

}
