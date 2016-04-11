package com.rui.pro1.modules.sys.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.rui.pro1.modules.sys.constants.SysComm;
import com.rui.pro1.modules.sys.service.impl.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(SysComm.SYS_USER, userService.getUser(username));
        return true;
    }
}
