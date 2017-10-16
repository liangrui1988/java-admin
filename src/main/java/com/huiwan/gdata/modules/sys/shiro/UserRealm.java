package com.huiwan.gdata.modules.sys.shiro;

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

import com.huiwan.gdata.common.utils.spring.SysApplicationContext;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.service.IRoleService;
import com.huiwan.gdata.modules.sys.service.IUserLoginService;
import com.huiwan.gdata.modules.sys.service.IUserService;
import com.huiwan.gdata.modules.sys.utils.PassUtil;

/**
 * 获取身份验证相关信息 .
 * 
 * 用于访问诸如用户、角色、权限这类安全数据的组件
 * 
 * @author ruiliang
 *
 */
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private IUserLoginService userLoginService;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	
//	@Autowired
	private PassUtil PassUtil;

	/**
	 * ------------------------------------------------------------------------
	 * by userName get user info,ok login
	 * -------------------------------------------------------------------------
	 */                         
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		if (userLoginService == null) {
			userLoginService = (IUserLoginService) SysApplicationContext
					.getBean("userLoginService");
		}
		
		if(userService==null){
			userService =  (IUserService) SysApplicationContext
					.getBean("userService");
		}

		// userService.findRoles(username)
		Set<String> roles = userService.getUserRole(username);
		authorizationInfo.setRoles(roles);
		// userService.findPermissions(username)
		Set<String> permissions = userService.getUserPermissions(username);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	/**
	 * ------------------------------------------------------------------------
	 * by token get user info,no login 未进行验证  db user info
	 * -------------------------------------------------------------------------
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();

		if (userLoginService == null) {
			userLoginService = (IUserLoginService) SysApplicationContext
					.getBean("userLoginService");

		}

		User user = userLoginService.getUser(username);
		if (user == null) {
			throw new UnknownAccountException();// NO ACCOUNT=userName
		}
		if (user.getStatus().intValue() == 2) {
			throw new LockedAccountException(); // userName Locked
		}
		// 简单的身份验证  // salt=username+salt  // 用户名
		ByteSource bs=ByteSource.Util.bytes(PassUtil.salt	+ user.getUserName());
		
//		System.out.println(user.getUserName()+"=="+user.getPassword());
//		System.out.println("bs:"+bs);

//		System.out.println(getName());
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), bs,getName());
		
//		System.out.println(info);
		
//		return new SimpleAuthenticationInfo(new Principal(user, token.isMobileLogin()), 
//				user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
		
		return info;
	}
}