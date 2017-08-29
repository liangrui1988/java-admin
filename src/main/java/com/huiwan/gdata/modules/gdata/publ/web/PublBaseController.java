package com.huiwan.gdata.modules.gdata.publ.web;

import org.springframework.stereotype.Controller;

import com.huiwan.gdata.common.annotatiions.MenuAnnot;
import com.huiwan.gdata.common.annotatiions.MenuAnnots;
import com.huiwan.gdata.modules.BaseController;

@Controller
@MenuAnnots({ @MenuAnnot(id = "publ", name = "游戏共性数据", parentId = "", href = "", sortNo = 5),
		@MenuAnnot(id = "publ_yuzk", name = "运营总况", parentId = "publ", href = "", sortNo = 1),
		@MenuAnnot(id = "publ_sssj", name = "实时数据", parentId = "publ", href = "", sortNo = 2),
		@MenuAnnot(id = "publ_zxl", name = "在线类", parentId = "publ", href = "", sortNo = 3),
		@MenuAnnot(id = "publ_zxl_xyqk", name = "活跃情况", parentId = "publ_zxl", href = "", sortNo = 2),
		@MenuAnnot(id = "publ_zxl_qxqk", name = "有效用户", parentId = "publ_zxl", href = "", sortNo = 3),
		@MenuAnnot(id = "publ_zxl_rzyh", name = "注册用户", parentId = "publ_zxl", href = "", sortNo = 1),
		@MenuAnnot(id = "publ_yhl", name = "用户类", parentId = "publ", href = "", sortNo = 4),
		@MenuAnnot(id = "publ_yxst", name = "游戏生态", parentId = "publ", href = "", sortNo = 5) })

public class PublBaseController extends BaseController {

}
