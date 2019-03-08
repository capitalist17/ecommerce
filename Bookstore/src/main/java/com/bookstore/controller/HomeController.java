package com.bookstore.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.service.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

//  Not required. Handled by other controllers
//	@RequestMapping("/myAccount")
//	public String myAccount() {
//		return "myAccount";
//	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("classActiveLogin", true);
		return "myAccount";
	}
	
	@RequestMapping("/forgotPassword")
	public String forgetPassword(Model model) {		
		model.addAttribute("classActiveForgotPassword", true);
		return "myAccount";
	}
	
	@RequestMapping("/newUser")
	public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
		PasswordResetToken passToken = userService.getPasswordResetToken(token);
		model.addAttribute("classActiveNewAccount", true);
		return "myAccount";
	}

}
