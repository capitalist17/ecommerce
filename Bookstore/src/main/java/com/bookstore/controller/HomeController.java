package com.bookstore.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
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
	public String newUser(Model model) {
		model.addAttribute("classActiveNewAccount", true);
		return "myAccount";
	}

}
