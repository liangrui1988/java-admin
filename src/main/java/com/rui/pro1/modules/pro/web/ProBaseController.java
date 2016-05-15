package com.rui.pro1.modules.pro.web;

import org.springframework.stereotype.Controller;

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.modules.BaseController;
@Controller
@MenuAnnot(id = Modules.PRO, name = "商家管理", parentId = "",href = "",sortNo=2)
public class ProBaseController extends BaseController {

}
