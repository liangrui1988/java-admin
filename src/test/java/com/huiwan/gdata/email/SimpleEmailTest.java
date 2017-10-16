package com.huiwan.gdata.email;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.junit.Test;

/**
 * http://commons.apache.org/proper/commons-email/userguide.html
 * 
 * @author ruiliang
 *
 */
public class SimpleEmailTest {

	public final String hostName = "smtp.163.com";
	public final String from = "rui_dev@163.com";
	public final String user = "rui_dev";
	public final String password = "rui_dev123456";

	public static void main(String[] args) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.163.com");
		// email.setSmtpPort(25);
		email.setSslSmtpPort("465");// 456

		// email.setHostName("smtp.googlemail.com");
		// email.setSmtpPort(465);
		// 登陆邮件服务器的用户名和密码
		email.setAuthenticator(new DefaultAuthenticator("rui_dev",
				"ruidev123456"));// @163.com
		email.setSSLOnConnect(true);

		// 发送人
		email.setFrom("rui_dev@163.com");// 设置字段的电子邮件使用指定的地址。电子邮件
		email.setSubject("送行-梁实秋");

		email.setMsg("中文测试，这是一个中文测试");
		// 接收人
		email.addTo("rui_dev@126.com");// 382453602@qq.com rui_dev@126.com
		email.send();

		System.out.println("xxxx");
	}

	@Test
	public void test1() throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.163.com");
		// email.setSmtpPort(25);
		email.setSslSmtpPort("465");// 456

		// email.setHostName("smtp.googlemail.com");
		// email.setSmtpPort(465);
		// 登陆邮件服务器的用户名和密码
		email.setAuthenticator(new DefaultAuthenticator("rui_dev",
				"ruidev123456"));// @163.com
		email.setSSLOnConnect(true);

		// 发送人
		email.setFrom("rui_dev@163.com");// 设置字段的电子邮件使用指定的地址。电子邮件
		email.setSubject("送行-梁实秋");

		email.setMsg("中文测试，这是一个中文测试");
		// 接收人
		email.addTo("rui_dev@126.com");// 382453602@qq.com rui_dev@126.com
		email.send();

		System.out.println("xxxx");

	}

	@Test
	public void attachmentTest() throws EmailException, MalformedURLException {
		EmailAttachment attachment = new EmailAttachment();
		// attachment
		// .setPath("C:/Users/Public/Pictures/Sample Pictures/befc0052e4021f50d6c8912214ae770f.jpg");
		attachment.setURL(new URL(
				"http://www.apache.org/images/asf_logo_wide.gif"));
	

		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Picture of John");
		attachment.setName("rui");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(hostName);
		email.addTo("rui_dev@126.com", "hirui");
		email.setFrom(from);
		email.setSubject("The picturex");
		email.setMsg("Here is the picture you wanted");

		email.setAuthenticator(new DefaultAuthenticator("rui_dev",
				"ruidev123456"));// @163.com

		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();

		System.out.println("send ok");
	}

	@Test
	public void htmlEmail() throws EmailException, MalformedURLException {

		// Create the email message
		HtmlEmail email = new HtmlEmail();
		email.setHostName(hostName);
		email.addTo("rui_dev@126.com", "hirui");
		email.setFrom(from);
//		email.setAuthenticator(new DefaultAuthenticator(user,
//				password));// @163.com
		
		email.setAuthenticator(new DefaultAuthenticator("rui_dev",
				"ruidev123456"));// @163.com
		
		email.setSubject("测试inline image");
		// embed the image and get the content id
		URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
		String cid = email.embed(url, "Apache logo");

		
		System.out.println("<html>The apache logo - <img src=\"cid:" + cid
				+ "\"></html>");
		// set the html message  <img src=\"cid:" + cid+ "\">
		
		email.setHtmlMsg("<html>The apache logo -</html>");

		// set the alternative message
		email.setTextMsg("Your email client does not support HTML messages");

		// send the email
		email.send();
	}
	
	
	@Test
	public void embedded() throws MalformedURLException, EmailException{
		
		// load your HTML email template
		 // String htmlEmailTemplate = "<img src=\"http://www.youboy.com/new/images/common/yb_search.png\">";

		  String htmlEmailTemplate = "http://www.youboy.com/new/images/common/yb_search.png";

		  // define you base URL to resolve relative resource locations
		  URL url = new URL("http://www.apache.org");

		  // create the email message
		  ImageHtmlEmail email = new ImageHtmlEmail();
		  email.setCharset("utf-8");
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName(hostName);
		  email.addTo("rui_dev@126.com", "hirui");
		  email.setFrom(from, "Me");
		  email.setSubject("Test email with inline image");
//		  email.setAuthenticator(new DefaultAuthenticator(user,
//			password));// @163.com
		  
		  
			email.setAuthenticator(new DefaultAuthenticator("rui_dev",
					"ruidev123456"));// @163.com
		  // set the html message
		  email.setHtmlMsg(htmlEmailTemplate);

		  // set the alternative message
		  email.setTextMsg("Your email client does not support HTML messages");

		  // send the email
		  email.send();
		
	}

	public static String getStr() {
		String context = "送行----梁实秋（那句话在最后）黯然消魂者，别而已矣。”邀想古人送别，也是一种雅人深致。古时交通不便，一去不知多久，再见不知何年，所以难甫唱只骊歌，霸桥折条扬柳，甚至在阳关敬一杯酒，都有意味。李白的船刚要起锭，汪伦老远的在岸上踏歌而来，那幅情景真是历历在目前。其妙处在于淳朴真挚，出之以潇洒自然。平夙莫逆于心，临别难分难舍。如果平常我看着你面目可憎，你觉得我语言无味，一旦远离，那是最好不过，只恨世界太小，唯恐将来有要碰头，何必送行？";
		context += "在现代人的生活里，送行是和拜寿送殡等等一样的成为应酬的礼节之一。揪着公鸡尾巴“起个大早，迷迷糊糊的赶到车站码头，挤在乱哄哄的人群里面，找到你的对象，扯几句淡话，好容易耗到汽笛一响，然后鸟兽散，吐一口轻松气，撅着大嘴回家。这叫做周到。在被送的那一方面，觉得好热闹，人缘好，没白混，而且体面，有这么多人舍不得我走，斜眼看着旁边的没有人送的旅客，相行之下，尤其容易起一种优越感，不禁精神抖擞，很不得对没一个送行的人握八次手，道十回谢。死人出殡，都讲究要有多少亲友执绋，表示恋恋不舍，何况活人？行色不可不壮。";

		return context += "我不愿送人，亦不愿人送我，对于自己真正舍不得离开的人，离别的那一刹那象是开刀，凡是开刀的场合照例是应该先用麻醉剂，使病人在迷梦中度过那场痛苦，所以离别的苦痛最好避免。一个朋友说，”你 走，我不送你，你来，无论多大风多大雨，我要去接你。“我最赏识那种心情。";

	}

}
