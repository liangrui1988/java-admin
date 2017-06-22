package com.huiwan.gdata.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huiwan.gdata.modules.sys.utils.UserUtils;

/**
 * BaseController
 * 
 * @date 现在你不认识我，我不认识你。十年之后， 我们是朋友
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
