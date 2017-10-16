package com.huiwan.gdata.modules.sys.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.huiwan.gdata.common.cache.EhCacheKeys;
import com.huiwan.gdata.modules.sys.constants.SysComm;

/**
 * 认证信息匹配器
 * 
 * @author ruilaing
 *
 */
public class CredentialsMatcher extends HashedCredentialsMatcher {

	private Cache<String, AtomicInteger> userLonginLogCache;

	public CredentialsMatcher(CacheManager cacheManager) {
		userLonginLogCache = cacheManager
				.getCache(EhCacheKeys.LONGIN_LOG_CACHE);
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// 登陆 + 1
		AtomicInteger loginCount = userLonginLogCache.get(username);
		if (loginCount == null) {
			loginCount = new AtomicInteger(0);
			userLonginLogCache.put(username, loginCount);
		}
		// 登陆限制
//		if (loginCount.incrementAndGet() >=SysComm.USER_LOGIN_COUNT) {
//			throw new CaptchaErrorException();
//		}
		if (loginCount.incrementAndGet() >=SysComm.USER_LOGIN_MAX_COUNT_LOCK) {
			throw new ExcessiveAttemptsException();
		}
		//
		
	
		// importance 关键点
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			userLonginLogCache.remove(username);
		}
		return matches;
	}
}
