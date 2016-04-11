package com.rui.pro1.modules.sys.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.service.IRoleService;
import com.rui.pro1.modules.sys.service.IUserService;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;

	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		// userService.findRoles(username)
		Set<String> roles = userService.getUserRole(username);
		authorizationInfo.setRoles(roles);

		// userService.findPermissions(username)
		Set<String> permissions = userService.getUserPermissions(username);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.getUser(username);
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		if (user.getStatus().intValue()==2) {
			throw new LockedAccountException(); // 帐号锁定
		}
		return new SimpleAuthenticationInfo(user.getUserName(), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes("*&^"),// salt=username+salt
				getName() // realm name
		);
	}
}