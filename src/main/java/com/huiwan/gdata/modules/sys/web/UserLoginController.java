package com.huiwan.gdata.modules.sys.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huiwan.gdata.common.bean.ResultBean;
import com.huiwan.gdata.common.cache.EhCacheKeys;
import com.huiwan.gdata.common.cache.SpringCacheManagerWrapper;
import com.huiwan.gdata.common.constants.RespHeaderConstans;
import com.huiwan.gdata.common.exception.MessageCode;
import com.huiwan.gdata.common.utils.IpUtil;
import com.huiwan.gdata.common.utils.http.WebHelp;
import com.huiwan.gdata.common.utils.spring.SysApplicationContext;
import com.huiwan.gdata.modules.sys.annotations.CurrentUser;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.constants.SysComm;
import com.huiwan.gdata.modules.sys.entity.Menu;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.exception.CaptchaErrorException;
import com.huiwan.gdata.modules.sys.service.IMenuService;
import com.huiwan.gdata.modules.sys.service.IUserLoginService;
import com.huiwan.gdata.modules.sys.service.IUserService;
import com.huiwan.gdata.modules.sys.shiro.TokenBuild;
import com.huiwan.gdata.modules.sys.shiro.jcaptcha.JCaptcha;
import com.huiwan.gdata.modules.sys.vo.UserLoginVo;

@Controller
// @RequestMapping("sys/user/")
public class UserLoginController extends SysBaseController {
	// protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserLoginService userLoginService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IMenuService menuService;

	// @RequestMapping(value = "login")//, method = RequestMethod.POST
	@ResponseBody
	public ResultBean login(HttpServletRequest request, HttpServletResponse response, UserLoginVo userLoginVo) {

		// Map<String, String[]> param=request.getParameterMap();

		ResultBean rb = new ResultBean();
		try {
			logger.debug("message:{}", userLoginVo);
			UserBean userBean = userLoginService.login(userLoginVo);

			if (userBean == null || userBean.getId() <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_NO_USER, "用户不存在");
				logger.warn("用户名登陆失败！userLoginVo:{}", userLoginVo);
				return rb;
			}

			rb.setData(userBean);
		} catch (Exception e) {
			logger.error("用户登陆异常:UserName:{} ,Message>>>{}", userLoginVo.getUserName(), e.getMessage());
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean logout(HttpServletRequest request, HttpServletResponse response, UserLoginVo userLoginVo) {
		ResultBean rb = new ResultBean();
		try {
			// int result = userLoginService.logout(userLoginVo);
			// if (result <= 0) {
			// rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			// }
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			rb.setMessageCode(MessageCode.USER_LOGOUT);
			rb.setMessage("退出系统成功");
		} catch (Exception e) {
			logger.error("用户登陆异常:UserName:{} ,Message>>>{}", userLoginVo.getUserName(), e.getMessage());
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	/**
	 * 用户登录 代码登陆方式
	 */
	@ResponseBody
	@RequestMapping(value = "login") // , method=RequestMethod.POST
	public ResultBean login2(HttpServletRequest request, HttpServletResponse req, UserLoginVo loginUser) {
		ResultBean rb = new ResultBean();

		if (loginUser == null || StringUtils.isBlank(loginUser.getUserName())
				|| StringUtils.isBlank(loginUser.getPassword())) {
			rb = new ResultBean(false, MessageCode.PLASS_LOGIN, "请登陆系统", "");
			return rb;
		}

		// 验证码处理
		ResultBean rbBean = capthchaProcess(request, loginUser.getUserName(), loginUser.getCaptcha());
		if (rbBean != null) {
			return rbBean;
		}

		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		// String host = request.getRemoteHost(); //主机名
		String host = IpUtil.getRemoteIp(request);
		try {
			// return new SimpleAuthenticationInfo(new Principal(user,
			// token.isMobileLogin()),
			// user.getPassword().substring(16), ByteSource.Util.bytes(salt),
			// getName());

			// 构造登陆令牌环
			TokenBuild token = new TokenBuild(loginUser.getUserName(), loginUser.getPassword().toCharArray(),
					rememberMe, host);

			// 发出登陆请求
			Subject sbuject = SecurityUtils.getSubject();
			sbuject.login(token);

			// 登陆成功
			// HttpSession session = request.getSession(true);
			try {
				UserBean user = userService.getUser(loginUser.getUserName());
				List<Menu> menus = userService.getUserMenus(loginUser.getUserName());
				if (menus != null) {
					user.setMenus(menus);
				}
				rb.setData(user);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new Exception(e);
			}

		} catch (UnknownAccountException e) {
			rb = new ResultBean(false, MessageCode.SYS_NO_USER, "账号不存在!", "");
		} catch (IncorrectCredentialsException e) {
			rb = new ResultBean(false, MessageCode.SYS_NO_USER_AND_PASSWORD, "用户或密码错误", "");
		} catch (CaptchaErrorException e) {
			rb = new ResultBean(false, MessageCode.PLASS_CAPTCHA, " 请输入验证码", "");
		} catch (ExcessiveAttemptsException e) {
			rb = new ResultBean(false, MessageCode.SYS_LOG_IN_TOO_MANY, "账户错误次数过多,暂时禁止登录!", "");
		} catch (Exception e) {
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "系统异常!");
		}
		return rb;
	}

	/**
	 * 跳转登陆页
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "loginPage") // , method=RequestMethod.POST
	public void loginPage(HttpServletRequest request, HttpServletResponse response) {
		// ModelAndView mv = new
		// ModelAndView("/user/save/result");//默认为forward模式
		// ModelAndView mv = new
		// ModelAndView("redirect:/user/save/result");//redirect模式
		// Subject sbuject=SecurityUtils.getSubject();
		// subject.isAuthenticated()表示用户进行了身份验证登录的，即使有Subject.login进行了登录；
		// subject.isRemembered()：表示用户是通过记住我登录的，此时可能并不是真正的你（如你的朋友使用你的电脑，或者你的cookie被窃取）在访问的；且两者二选一，即subject.isAuthenticated()==true，则subject.isRemembered()==false；反之一样。
		// UsernamePasswordToken token = null;
		// if (!sbuject.isAuthenticated() && sbuject.isRemembered())
		// {
		// Object principal = sbuject.getPrincipal();
		// if (null != principal) {
		// String userName = (String) principal;
		// System.out.println(userName);
		// //刷新用户
		// token = new UsernamePasswordToken(userName, mem.getLoginPassword());
		// token.setRememberMe(true);
		// subject.login(token);// 登录
		// }
		// } else {
		//
		// //省略代码-里面是一个新的token 生成
		// }

		// 获取用户信息 好像没必要 null?
		Subject sbuject = SecurityUtils.getSubject();
		Object principal = sbuject.getPrincipal();
		// System.out.println(principal);
		Map<String, String> map = new HashMap<String, String>();
		if (principal != null && !"".equals(principal)) {
			SpringCacheManagerWrapper cacheManager = (SpringCacheManagerWrapper) SysApplicationContext
					.getBean("cacheManager");
			Cache<String, AtomicInteger> cache = cacheManager.getCache(EhCacheKeys.LONGIN_LOG_CACHE);
			AtomicInteger loginCount = cache.get(principal.toString());
			if (loginCount != null && loginCount.get() >= SysComm.USER_LOGIN_COUNT) {
				// rb = new
				// ResultBean(false,MessageCode.PLASS_CAPTCHA,"请输入验证码","");
				// 是否显示验证码
				map.put("isCaptcha", "1");

			}

		}

		if (WebHelp.isAjAxRequest(request)) {

			// Cookie[] coi=request.getCookies();
			// for(Cookie c:coi){
			// System.out.println(c);
			// System.out.println("k:"+c.getName()+",age:"+c.getMaxAge()+",v:"+c.getValue()+",p:"+c.getPath()+",Domain："+c.getDomain()+",Version:"+c.getVersion()+",Secure:"+c.getSecure()+",Comment:"+c.getComment());
			// }

			ResultBean rb = new ResultBean();
			if (map.containsKey("isCaptcha")) {
				response.setHeader(RespHeaderConstans.AJAX_REQUEST_HEADER,
						RespHeaderConstans.Code.AJAX_REQUEST_HEADER_004);
				rb.setSuccess(false);
				rb.setMessageCode(MessageCode.PLASS_CAPTCHA);
				rb.setMessage("请输入验证码");
			} else {
				response.setHeader(RespHeaderConstans.AJAX_REQUEST_HEADER,
						RespHeaderConstans.Code.AJAX_REQUEST_HEADER_001);
				rb.setSuccess(false);
				rb.setMessageCode(MessageCode.PLASS_LOGIN);
				rb.setMessage("请登陆系统");
			}

			WebHelp.responseResultBean(request, response, rb);
		} else {
			try {
				if (map.containsKey("isCaptcha")) {
					response.sendRedirect(request.getContextPath() + "/views/login.html?isCaptcha=1");
				} else {
					response.sendRedirect(request.getContextPath() + "/views/login.html");

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 跳转页
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "unauthorized") // , method=RequestMethod.POST
	public void unauthorized(HttpServletRequest request, HttpServletResponse response) {
		if (WebHelp.isAjAxRequest(request)) {
			ResultBean rb = new ResultBean();
			response.setHeader(RespHeaderConstans.AJAX_REQUEST_HEADER, RespHeaderConstans.Code.AJAX_REQUEST_HEADER_002);
			rb.setSuccess(false);
			rb.setMessageCode(MessageCode.SYS_NO_PERMISSE);
			rb.setMessage("没有权限");
			WebHelp.responseResultBean(request, response, rb);
		} else {
			try {
				response.sendRedirect(request.getContextPath() + "/views/401.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// @RequestMapping(value = "/login")
	public String showLoginForm(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		model.addAttribute("error", error);
		return "login";
	}

	@RequestMapping("/")
	public String index(@CurrentUser User loginUser) {
		if (loginUser == null) {
			return "/loginPage";
		}
//		List<Menu> menus = userService.getUserMenus(loginUser.getUserName());
		// model.addAttribute("menus", menus);
		// return "/views/main.html";
		return "/views/index.html";

	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	private ResultBean capthchaProcess(HttpServletRequest request, String userName, String captcha)

	{
		ResultBean rb = null;

		// 是否需要验证码
		// or springCacheManager
		SpringCacheManagerWrapper cacheManager = (SpringCacheManagerWrapper) SysApplicationContext
				.getBean("cacheManager");
		Cache<String, AtomicInteger> cache = cacheManager.getCache(EhCacheKeys.LONGIN_LOG_CACHE);
		AtomicInteger loginCount = cache.get(userName);
		if (loginCount != null && loginCount.get() >= SysComm.USER_LOGIN_COUNT) {
			if (StringUtils.isBlank(captcha)) {
				rb = new ResultBean(false, MessageCode.PLASS_CAPTCHA, "请输入验证码", "");
				return rb;
			}

			boolean captchaResult = JCaptcha.validateResponse(request, captcha);
			if (!captchaResult) {
				System.out.println(captchaResult);
				rb = new ResultBean(false, MessageCode.CAPTCHA_ERROR, "验证码不正确", "");
				return rb;
			}
		}

		return rb;
	}

	/**
	 * 异常处理
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "exception") // , method=RequestMethod.POST
	public void exception(HttpServletRequest request, HttpServletResponse response) {
		if (WebHelp.isAjAxRequest(request)) {
			ResultBean rb = new ResultBean();
			response.setHeader(RespHeaderConstans.AJAX_REQUEST_HEADER, RespHeaderConstans.Code.AJAX_REQUEST_HEADER_003);
			rb.setSuccess(false);
			rb.setMessageCode(MessageCode.SYS_ERROR);
			rb.setMessage("系统异常");
			WebHelp.responseResultBean(request, response, rb);
		} else {
			try {
				response.sendRedirect(request.getContextPath() + "/views/500.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		TokenBuild token = new TokenBuild("admin", "admin".toCharArray(), true, "0:0:0:1");

	}
}
