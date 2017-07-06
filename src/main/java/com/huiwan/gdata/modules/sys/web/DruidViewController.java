package com.huiwan.gdata.modules.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.constants.Modules;
import com.huiwan.gdata.common.constants.uri.SysUri;

/**
 * 系统参数
 * 
 * @author rui
 *
 */
@Controller
@RequestMapping(SysUri.SYS_DRUID)
@MenuAnnot(id = "sys:druid", name = "数据库监控", parentId = Modules.SYS, href = "/druid/index", sortNo = 7)
public class DruidViewController extends SysBaseController {


//	@RequestMapping(value = "index", method = RequestMethod.GET)
//	public String getList(
//			@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
//			@RequestParam(value = "pagesize", defaultValue = "12") Integer pagesize,
//			Parameter parameter) {
//	
//
//        return "redirect:/ toList "; 
//	}

}
