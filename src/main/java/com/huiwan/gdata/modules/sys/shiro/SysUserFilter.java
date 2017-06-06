package com.huiwan.gdata.modules.sys.shiro;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.huiwan.gdata.common.utils.spring.SysApplicationContext;
import com.huiwan.gdata.modules.sys.constants.SysComm;
import com.huiwan.gdata.modules.sys.service.IUserService;


public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private IUserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        if (userService == null) {
        	userService = (IUserService) SysApplicationContext
					.getBean("userService");
		}
        request.setAttribute(SysComm.SYS_USER, userService.getUser(username));
        return true;
    }
    
    
}
