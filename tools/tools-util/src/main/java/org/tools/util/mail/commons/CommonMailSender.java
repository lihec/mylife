package org.tools.util.mail.commons;

import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.tools.util.mail.MailConstants;

public class CommonMailSender {

	public static boolean sendNotice(String msg) {
		Email email = new SimpleEmail();
		email.setHostName(MailConstants.smtp163.getHostname());
		email.setSmtpPort(MailConstants.smtp163.getPort());
		email.setAuthenticator(new DefaultAuthenticator(MailConstants.smtp163
				.getFormAddress(), MailConstants.smtp163.getPwd()));
		email.setSSLOnConnect(true);
		try {
			email.setFrom(MailConstants.smtp163.getFormAddress(), "贺");
			email.addTo("13776607509@139.com");
			email.setSubject("日程");
			email.setMsg(msg);
			email.send();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void sentNoticeSlice(String msg) {
		int len = msg.length();
		int loop = (int) Math.ceil(new Double(len) / 65);
		for (int i = 1; i <= loop; i++) {
			String subMsg = msg.substring((i - 1) * 65, i * 65 > len ? len
					: i * 65);
			sendNotice(subMsg);
		}

	}

	public static void sendSimpleMail() {
		Email email = new SimpleEmail();
		email.setHostName(MailConstants.smtp163.getHostname());
		email.setSmtpPort(MailConstants.smtp163.getPort());
		email.setAuthenticator(new DefaultAuthenticator(MailConstants.smtp163
				.getFormAddress(), MailConstants.smtp163.getPwd()));
		email.setSSLOnConnect(true);
		try {
			email.setFrom("13776607509@163.com", "贺");
			email.setSubject("TestMail");
			email.setMsg("This is a test mail ... :-)");
			email.addTo("13776607509@139.com");
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendMultiPartMail() {
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		try {
			attachment.setURL(new URL(
					"http://www.apache.org/images/asf_logo_wide.gif"));
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Apache logo");
			attachment.setName("Apache logo");

			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(MailConstants.smtp163.getHostname());
			email.setSmtpPort(MailConstants.smtp163.getPort());
			email.setAuthenticator(new DefaultAuthenticator(MailConstants.smtp163
					.getFormAddress(), MailConstants.smtp163.getPwd()));
			email.setSSLOnConnect(true);

			email.addTo("13776607509@139.com", "John Doe");
			email.setFrom("13776607509@163.com", "贺");
			email.setSubject("The logo");
			email.setMsg("Here is Apache's logo");
			// add the attachment
			email.attach(attachment);

			// send the email
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void sendHtmlMail() {
		// Create the email message
		HtmlEmail email = new HtmlEmail();
		email.setHostName(MailConstants.smtp163.getHostname());
		email.setSmtpPort(MailConstants.smtp163.getPort());
		email.setAuthenticator(new DefaultAuthenticator(MailConstants.smtp163
				.getFormAddress(), MailConstants.smtp163.getPwd()));
		email.setSSLOnConnect(true);
		try {
			email.addTo("13776607509@139.com");
			email.setFrom("13776607509@163.com", "贺");
			email.setSubject("Test email with inline image");

			// embed the image and get the content id
			URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
			String cid = email.embed(url, "Apache logo");

			// set the html message
			email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" + cid
					+ "\"></html>");

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");

			// send the email
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// CommonMailSender.sendSimpleMail();
		// CommonMailSender.sendMultiPartMail();
		// CommonMailSender.sendHtmlMail();
		CommonMailSender
				.sentNoticeSlice("1、周末去中信银行2、去超市买洗发水3、招商银行存款4、刷卡机5、租房6、不知道干嘛7、电影院看电影8、新街口买衣服9、财务管理");
	}
}
