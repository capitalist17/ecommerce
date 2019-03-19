package com.bookstore.utility;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bookstore.domain.Order;
import com.bookstore.domain.User;

@Component
public class MailConstructor {
	@Autowired
	private Environment env;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public SimpleMailMessage constructResetTokenEmail(
			String contextPath, Locale locale, String token, User user, String password
			) {
		
		String url = contextPath + "/newUser?token="+token;
		String message = "\nPlease click on this link to verify your email and edit your personal information. Your password is: \n"+password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Bookstore - New User");
		email.setText(url+message);
		email.setFrom(env.getProperty("support.email"));
		return email;
		
	}
	
	// MimeMessagePreparator message can contain rich content like html in the email body. 
	public MimeMessagePreparator constructOrderConfirmationEmail(User user, Order order, Locale english) {
		Context context = new Context(); // create a thymeleaf context
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		// there is a html file by name orderConfirmationEmailTemplate which acts as a template
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);

		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Order Confirmation - "+order.getId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("asnspring@gmail.com"));
			}
		};
		
		return messagePreparator;

	}
}
