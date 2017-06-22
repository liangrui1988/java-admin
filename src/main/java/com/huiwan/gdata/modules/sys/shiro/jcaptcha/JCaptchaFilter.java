package com.huiwan.gdata.modules.sys.shiro.jcaptcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

//import org.apache.shiro.web.servlet.OncePerRequestFilter;
/**
 * 用于生成验证码图片的过滤器
 * 
 * @author ruiliang
 *
 */
public class JCaptchaFilter extends OncePerRequestFilter {

	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.setDateHeader("Expires", 0L);
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
	
		//从 cookie 中获取
//		String id = request.getRequestedSessionId();
//		if(StringUtils.isBlank(id)){
		String id =request.getSession().getId();
	//	}
		
		//使用当前会话ID当作key获取相应的验证码图片
		BufferedImage bi = JCaptcha.captchaService.getImageChallengeForID(id);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}

}