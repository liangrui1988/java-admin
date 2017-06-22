package com.huiwan.gdata.modules.sys.web.interceptor;

import java.util.HashSet;
import java.util.Set;

public class SetData {

	static Set<String> whiteList = new HashSet<String>();

	// /views/** = anon

	static {
		whiteList.add("/logout");
		whiteList.add("/login");
		whiteList.add("/unauthorized");
		whiteList.add("/jcaptcha");
		whiteList.add("/loginPage");

	}
}
