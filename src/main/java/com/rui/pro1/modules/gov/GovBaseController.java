package com.rui.pro1.modules.gov;

import org.springframework.stereotype.Controller;

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.modules.BaseController;
@Controller
@MenuAnnot(id = Modules.GOV, name = "政府管理", parentId = "",href = "",sortNo=3)
public class GovBaseController   extends BaseController{

}
