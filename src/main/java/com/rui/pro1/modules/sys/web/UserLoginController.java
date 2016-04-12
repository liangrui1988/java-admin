package com.rui.pro1.modules.sys.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.exception.ErrorCode;
import com.rui.pro1.modules.sys.bean.UserBean;
import com.rui.pro1.modules.sys.constants.SysComm;
import com.rui.pro1.modules.sys.service.IUserLoginService;
import com.rui.pro1.modules.sys.shiro.FormsAuthenticationFilter;
import com.rui.pro1.modules.sys.shiro.TokenBuild;
import com.rui.pro1.modules.sys.vo.UserLoginVo;

@Controller
//@RequestMapping("sys/user/")
public class UserLoginController extends SysBaseControoler {
//	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserLoginService userLoginService;

	//@RequestMapping(value = "login")//, method = RequestMethod.POST
	@ResponseBody
	public ResultBean login(HttpServletRequest request, HttpServletResponse response,
			UserLoginVo userLoginVo) {
		ResultBean rb = new ResultBean();
		try {
			logger.debug("message:{}",userLoginVo);
			UserBean userBean = userLoginService.login(userLoginVo);
			
			if(userBean==null||userBean.getId()<=0){
				rb = new ResultBean(false, ErrorCode.SYS_NO_USER, "用户不存在");
			    logger.warn("用户名登陆失败！userLoginVo:{}",userLoginVo);
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
	public ResultBean logout(HttpServletRequest request, HttpServletResponse response,
			UserLoginVo userLoginVo) {
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
	
	
	 /** 
     * 用户登录 
     */  
    @RequestMapping(value="/login")  //, method=RequestMethod.POST
    public String login2(HttpServletRequest request,HttpServletResponse req){  
        String resultPageURL = InternalResourceViewResolver.FORWARD_URL_PREFIX + "/";  
        String username = request.getParameter("userName");  
        String password = request.getParameter("password");  
        String rememberMe=WebUtils.getCleanParam(request, "rememberMe");
        
        String host=request.getRemoteHost();
        
        //获取HttpSession中的验证码  
//        String verifyCode = (String)request.getSession().getAttribute("verifyCode");  
//        //获取用户请求表单中输入的验证码  
//        String submitCode = WebUtils.getCleanParam(request, "verifyCode");  
//        System.out.println("用户[" + username + "]登录时输入的验证码为[" + submitCode + "],HttpSession中的验证码为[" + verifyCode + "]");  
//        if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(verifyCode, submitCode.toLowerCase())){  
//            request.setAttribute("message_login", "验证码不正确");  
//            return resultPageURL;  
//        }  
        
        boolean remeber=false;
        if("true".equals(rememberMe)){
        	remeber=true;
        }
        //token
       UsernamePasswordToken token = new UsernamePasswordToken(username, password,remeber,host);  
        
        //token.setRememberMe(true);  
        System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  
        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();  
        try {  
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            System.out.println("对用户[" + username + "]进行登录验证..验证开始");  
            currentUser.login(token);  
            System.out.println("对用户[" + username + "]进行登录验证..验证通过");  
            resultPageURL = "main";  
        }catch(UnknownAccountException uae){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
            request.setAttribute("message_login", "未知账户");  
        }catch(IncorrectCredentialsException ice){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");  
            request.setAttribute("message_login", "密码不正确");  
        }catch(LockedAccountException lae){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
            request.setAttribute("message_login", "账户已锁定");  
        }catch(ExcessiveAttemptsException eae){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");  
            request.setAttribute("message_login", "用户名或密码错误次数过多");  
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");  
            ae.printStackTrace();  
            request.setAttribute("message_login", "用户名或密码不正确");  
        }  
        //验证是否登录成功  
        if(currentUser.isAuthenticated()){  
            System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");  
        }else{  
            token.clear();  
        }  
        return resultPageURL;  
    }  
}
