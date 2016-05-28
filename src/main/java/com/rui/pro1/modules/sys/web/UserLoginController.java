package com.rui.pro1.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.exception.ErrorCode;
import com.rui.pro1.modules.sys.annotations.CurrentUser;
import com.rui.pro1.modules.sys.bean.UserBean;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.service.IMenuService;
import com.rui.pro1.modules.sys.service.IUserLoginService;
import com.rui.pro1.modules.sys.service.IUserService;
import com.rui.pro1.modules.sys.shiro.TokenBuild;
import com.rui.pro1.modules.sys.vo.UserLoginVo;

import freemarker.template.utility.StringUtil;

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
	public ResultBean login(HttpServletRequest request,
			HttpServletResponse response, UserLoginVo userLoginVo) {
		
        Map<String, String[]> param=request.getParameterMap();
		
		ResultBean rb = new ResultBean();
		try {
			logger.debug("message:{}", userLoginVo);
			UserBean userBean = userLoginService.login(userLoginVo);

			if (userBean == null || userBean.getId() <= 0) {
				rb = new ResultBean(false, ErrorCode.SYS_NO_USER, "用户不存在");
				logger.warn("用户名登陆失败！userLoginVo:{}", userLoginVo);
				return rb;
			}

			rb.setData(userBean);
		} catch (Exception e) {
			logger.error("用户登陆异常:UserName:{} ,Message>>>{}",
					userLoginVo.getUserName(), e.getMessage());
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean logout(HttpServletRequest request,
			HttpServletResponse response, UserLoginVo userLoginVo) {
		ResultBean rb = new ResultBean();
		try {
			int result = userLoginService.logout(userLoginVo);
			if (result <= 0) {
				rb = new ResultBean(false, ErrorCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			logger.error("用户登陆异常:UserName:{} ,Message>>>{}",
					userLoginVo.getUserName(), e.getMessage());
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

//	@Autowired
//	com.rui.pro1.modules.sys.shiro.CredentialsMatcher  cmatcher;
	/**
	 * 用户登录  代码登陆方式 测试
	 */
	@ResponseBody
	 @RequestMapping(value="login") //, method=RequestMethod.POST
	public ResultBean login2(HttpServletRequest request, HttpServletResponse req, User loginUser) {
	     ResultBean rb = new ResultBean();
	     
	     if(loginUser==null||StringUtils.isBlank(loginUser.getUserName())||StringUtils.isBlank(loginUser.getPassword()))
	     {
	        rb = new ResultBean(false,ErrorCode.ARGUMENT_ILLEGAL,"系统参数不合法","");
	        return rb;
	     }
	     
	     
		 boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM); 
	     String host = request.getRemoteHost();  
	       
	    
	        try{ 
	        	 //构造登陆令牌环  
		        TokenBuild token = new TokenBuild(loginUser.getUserName(), loginUser.getPassword().toCharArray(), rememberMe,host);  
	        	
	            //发出登陆请求  
		        Subject sbuject=SecurityUtils.getSubject();
		        sbuject.login(token);  
		        
	            //登陆成功  
	           // HttpSession session = request.getSession(true);  
	            try {  
	            	User user=	userService.getUser(loginUser.getUserName());
	        		List<Menu> menus = userService.getUserMenus(loginUser.getUserName());
	        		if(menus!=null){
		        		user.setMenus(menus);
	        		}
	        		rb.setData(user);
	            } catch (Exception e) {  
	                logger.error(e.getMessage(), e);  
	                throw new Exception(e);
	            }  
	        
	        }catch (UnknownAccountException e){  
	            rb = new ResultBean(false,ErrorCode.SYS_NO_USER,"账号不存在!","");
	        }catch (IncorrectCredentialsException e){  
	            rb = new ResultBean(false,ErrorCode.SYS_NO_USER_AND_PASSWORD,"用户或密码错误","");
	        }catch (ExcessiveAttemptsException e) {  
	            rb = new ResultBean(false,ErrorCode.SYS_LOG_IN_TOO_MANY,"账户错误次数过多,暂时禁止登录!","");
//	        }catch (ValidCodeException e){  
	      //      rb = new ResultBean(false,ErrorCode.SYS_VERIFICATION_CODE_ERROR,"验证码输入错误","");

	        }catch (Exception e){  
	            rb = new ResultBean(false,ErrorCode.SYS_ERROR,"系统异常!");
	        }  
	        return rb;  
	}

	//@RequestMapping(value = "/login")
	public String showLoginForm(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req
				.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(
				exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		model.addAttribute("error", error);
		return "login";
	}

	@RequestMapping("/")
	public String index(@CurrentUser User loginUser) {
		
		System.out.println(loginUser);
		if(loginUser==null){
			System.out.println(loginUser);
			return "index";
		}
		System.out.println(loginUser);
		List<Menu> menus = userService.getUserMenus(loginUser.getUserName());
		//model.addAttribute("menus", menus);
		System.out.println(menus);
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
}
