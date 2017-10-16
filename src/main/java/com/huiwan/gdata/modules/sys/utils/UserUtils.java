package com.huiwan.gdata.modules.sys.utils;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huiwan.gdata.common.utils.copyo.BeanCopierUtils;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.service.IUserService;

/**
 * 身份信息获取<br>
 * Subject是Shiro的核心对象，基本所有身份验证、授权都是通过Subject完成。<br>
 * Object getPrincipal(); //Primary Principal<br>
 * PrincipalCollection getPrincipals(); // PrincipalCollection
 * 
 * @author ruiliang
 *
 */
@Component
public class UserUtils {
	
	public static final String ROLE_MARK="role_mark";

	// @Autowired(required=false)
	// private UserMapper userMapper;
	@Autowired
	private IUserService userService;

	public String getCurrentName() {
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		return username;
	}

	/**
	 * 获取当前用户Bean
	 * 
	 * @return 取不到返回 new User()
	 */
	public UserBean getUserBean() {

		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		if (!StringUtils.isBlank(username)) {
			User user = userService.queryByUserName(username);
			if (user != null) {
				UserBean userBean = new UserBean();
				BeanCopierUtils.copyProperties(user, userBean);
				return userBean;
			}
			return new UserBean();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new UserBean();
	}

	/**
	 * 获取当前用户 业务中请不要调用这个返回前端，包含密码
	 * 
	 * @return 取不到返回 new User()
	 */
	@Deprecated
	public User getUserEntity() {

		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		if (!StringUtils.isBlank(username)) {
			User user = userService.queryByUserName(username);
			if (user != null) {
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取用户权限集合
	 * 
	 * @return
	 */
	public Set<String> getUserPermisson() {
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		return userService.getUserPermissions(username);
	}

	// --------------------------------------------------------------------------
	// ------------------只做个示例如何获取，可直接使用SecurityUtils相关方法 start------------
	// --------------------------------------------------------------------------

	/**
	 * 获取 Subject
	 * 
	 * @return Subject
	 */
	public Subject getSubject() {
		Subject subject = SecurityUtils.getSubject();

		return subject;

	}

	/**
	 * 判断当前用角色
	 * 
	 * @param roleIdentifier
	 * @return
	 */
	public boolean hasRole(String roleIdentifier) {
		Subject subject = SecurityUtils.getSubject();
		return subject.hasRole(roleIdentifier);

	}

	/**
	 * 判断当前用权限
	 * 
	 * @param isPermitted
	 * @return
	 */
	public boolean isPermitted(String permission) {
		Subject subject = SecurityUtils.getSubject();
		return subject.isPermitted(permission);

	}

	/**
	 * 获取当前session
	 * 
	 * @param isPermitted
	 * @return
	 */
	public Session getSession() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(); // 相当于getSession(true)
		return session;
	}

	/**
	 * 获取当前session 中用户选 择的角色标识
	 *
	 * @return
	 */
	public String getCurrentMark() {
		Subject subject = SecurityUtils.getSubject();
		Object role_mark = subject.getSession().getAttribute(ROLE_MARK);
		if (role_mark==null||StringUtils.isBlank(role_mark.toString())) {
			role_mark = "zl";
		}
		return role_mark.toString();
	}

	/**
	 * 获取当前session 中用户选 择的角色标识
	 * <p>
	 * 服务层方便调用
	 *
	 * @return
	 */
	public static String getStaticCurrentMark() {
		Subject subject = SecurityUtils.getSubject();
		Object role_mark = subject.getSession().getAttribute(ROLE_MARK);
		if (role_mark==null||StringUtils.isBlank(role_mark.toString())) {
			role_mark = "zl";
		}
		return role_mark.toString();
	}

	// --------------------------------------------------------------------------
	// ------------------只做个示例如何获取，可直接使用SecurityUtils相关方法 end--------------
	// --------------------------------------------------------------------------

	public static void main(String[] args) {
		System.out.println("xx");
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject);
		String username = (String) subject.getPrincipal();
		System.out.println(username);
	}

}
/**
 * Subject是Shiro的核心对象，基本所有身份验证、授权都是通过Subject完成。 1、身份信息获取 Java代码 Object
 * getPrincipal(); //Primary Principal PrincipalCollection getPrincipals(); //
 * PrincipalCollection
 * 
 * 2、身份验证 Java代码 void login(AuthenticationToken token) throws
 * AuthenticationException; boolean isAuthenticated(); boolean isRemembered();
 * 通过login登录，如果登录失败将抛出相应的AuthenticationException，如果登录成功调用isAuthenticated就会返回true
 * ，即已经通过身份验证；如果isRemembered返回true，表示是通过记住我功能登录的而不是调用login方法登录的。isAuthenticated/
 * isRemembered是互斥的，即如果其中一个返回true，另一个返回false。
 * 
 * 3、角色授权验证 Java代码 收藏代码 boolean hasRole(String roleIdentifier); boolean[]
 * hasRoles(List<String> roleIdentifiers); boolean hasAllRoles(Collection
 * <String> roleIdentifiers); void checkRole(String roleIdentifier) throws
 * AuthorizationException; void checkRoles(Collection<String> roleIdentifiers)
 * throws AuthorizationException; void checkRoles(String... roleIdentifiers)
 * throws AuthorizationException;
 * hasRole*进行角色验证，验证后返回true/false；而checkRole*验证失败时抛出AuthorizationException异常。
 * 
 * 4、权限授权验证 Java代码 boolean isPermitted(String permission); boolean
 * isPermitted(Permission permission); boolean[] isPermitted(String...
 * permissions); boolean[] isPermitted(List<Permission> permissions); boolean
 * isPermittedAll(String... permissions); boolean isPermittedAll(Collection
 * <Permission> permissions); void checkPermission(String permission) throws
 * AuthorizationException; void checkPermission(Permission permission) throws
 * AuthorizationException; void checkPermissions(String... permissions) throws
 * AuthorizationException; void checkPermissions(Collection
 * <Permission> permissions) throws AuthorizationException;
 * isPermitted*进行权限验证，验证后返回true/false；而checkPermission*
 * 验证失败时抛出AuthorizationException。
 * 
 * 5、会话 Java代码 Session getSession(); //相当于getSession(true) Session
 * getSession(boolean create);
 * 类似于Web中的会话。如果登录成功就相当于建立了会话，接着可以使用getSession获取；如果create=false如果没有会话将返回null，
 * 而create=true如果没有会话会强制创建一个。
 * 
 * 6、退出 Java代码 void logout();
 * 
 * 7、RunAs Java代码 void runAs(PrincipalCollection principals) throws
 * NullPointerException, IllegalStateException; boolean isRunAs();
 * PrincipalCollection getPreviousPrincipals(); PrincipalCollection
 * releaseRunAs(); RunAs即实现“允许A假设为B身份进行访问”；通过调用subject.runAs(b)进行访问；接着调用subject.
 * getPrincipals将获取到B的身份；此时调用isRunAs将返回true；而a的身份需要通过subject.
 * getPreviousPrincipals获取；如果不需要RunAs了调用subject. releaseRunAs即可。
 * 
 * 8、多线程 Java代码 <V> V execute(Callable<V> callable) throws ExecutionException;
 * void execute(Runnable runnable); <V> Callable<V> associateWith(Callable
 * <V> callable); Runnable associateWith(Runnable runnable);
 * 实现线程之间的Subject传播，因为Subject是线程绑定的；因此在多线程执行中需要传播到相应的线程才能获取到相应的Subject。
 * 最简单的办法就是通过execute(runnable/callable实例)直接调用；或者通过associateWith(runnable/
 * callable实例)得到一个包装后的实例；它们都是通过：1、把当前线程的Subject绑定过去；2、在线程执行结束后自动释放。
 * 
 * Subject自己不会实现相应的身份验证/授权逻辑，而是通过DelegatingSubject委托给SecurityManager实现；
 * 及可以理解为Subject是一个面门。
 * 
 * 对于Subject的构建一般没必要我们去创建；一般通过SecurityUtils.getSubject()获取： Java代码 public static
 * Subject getSubject() { Subject subject = ThreadContext.getSubject(); if
 * (subject == null) { subject = (new Subject.Builder()).buildSubject();
 * ThreadContext.bind(subject); } return subject; }
 * 即首先查看当前线程是否绑定了Subject，如果没有通过Subject.Builder构建一个然后绑定到现场返回。
 * 
 * 如果想自定义创建，可以通过： Java代码 收藏代码 new
 * Subject.Builder().principals(身份).authenticated(true/false).buildSubject()
 * 这种可以创建相应的Subject实例了，然后自己绑定到线程即可。在new
 * Builder()时如果没有传入SecurityManager，自动调用SecurityUtils.getSecurityManager获取；
 * 也可以自己传入一个实例。
 * 
 * 对于Subject我们一般这么使用： 1、身份验证（login） //2、授权（hasRole isPermitted 或checkRole
 * checkPermission） 3、将相应的数据存储到会话（Session） 4、切换身份（RunAs）/多线程身份传播 5、退出
 * 而我们必须的功能就是1、2、5。到目前为止我们就可以使用Shiro进行应用程序的安全控制了，但是还是缺少如对Web验证、Java方法验证等的一些简化实现。
 ***/