package com.rui.pro1.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rui.pro1.modules.sys.utils.UserUtils;

/**
 * BaseController
 * 
 * @date 十年之后
 * @author ruiliang
 * @qq 1067165280
 *
 */
public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 用户util组件
	 */
	@Autowired
	public UserUtils userUtils;

}
