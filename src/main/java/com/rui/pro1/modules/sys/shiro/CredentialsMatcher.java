package com.rui.pro1.modules.sys.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.rui.pro1.common.cache.EhCacheKeys;
import com.rui.pro1.modules.sys.exception.CaptchaErrorException;

import java.util.concurrent.atomic.AtomicInteger;

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
		if (loginCount.incrementAndGet() >=5) {
			throw new CaptchaErrorException();
		}
		if (loginCount.incrementAndGet() >=100) {
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
