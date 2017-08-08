package com.huiwan.gdata.modules.gdata.cat;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huiwan.gdata.common.bean.ResultBean;
import com.huiwan.gdata.common.utils.web.EmailUtil;
import com.huiwan.gdata.modules.gdata.cat.bean.EmailBean;

/**
 * cat 相关服务
 * 
 * @author rui
 *
 */
@Controller
@RequestMapping("cat/send")
public class SendController {

	/**
	 * 邮件发送
	 * 
	 * @param paginator
	 * @param bean
	 */
	@RequestMapping("email")
	@ResponseBody
	public ResultBean email( EmailBean bean) {
		System.out.println(bean);
		try {
			EmailUtil.sendContextEmailHTML(bean.getValue(), "cat监控平台email", bean.getRe(), "cat发送");
//			EmailUtil.sendContextEmail(bean.getValue(), "cat监控平台email", bean.getTo(), "cat发送");
		} catch (EmailException e) {
			e.printStackTrace();
			return new ResultBean(false, e.getMessage());
		}
		return new ResultBean(true);
	}
	@ResponseBody
	@RequestMapping(value = { "sms" })
	public ResultBean sms( EmailBean bean) {
		System.out.println(bean);
		try {
			EmailUtil.sendContextEmailHTML(bean.getValue(), "cat监控平台sms", "1067165280@qq.com", "cat发送sms");
		} catch (EmailException e) {
			e.printStackTrace();
			return new ResultBean(false, e.getMessage());
		}
		return new ResultBean(true);
	}
	@ResponseBody
	@RequestMapping(value = "weixin")
	public ResultBean weixin(EmailBean bean) {
		System.out.println(bean);
		try {
			EmailUtil.sendContextEmailHTML(bean.getValue(), "cat监控平台weixin", "1067165280@qq.com", "cat发送weixin");
		} catch (EmailException e) {
			e.printStackTrace();
			return new ResultBean(false, e.getMessage());
		}
		return new ResultBean(true);

	}
	
	
	public static void main(String[] args) throws EmailException {
		System.out.println("xx");
		EmailUtil.sendContextEmailHTML("abcd", "cat监控平台email", "1067165280@qq.com", "cat发送");

	}
	

}
