package com.png.web.controller;

import com.png.auth.service.UserService;
import com.png.data.entity.User;
import com.png.exception.BaseException;
import com.png.exception.EmailVerificationExpiredException;
import com.png.exception.InvalidEmailVerificationCodeException;
import com.png.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.sql.Timestamp;
import java.util.HashMap;

@Controller
public class PnGController {

	@Autowired
	UserService userService;
		
	@RequestMapping("/")
	public String milestone() {
		System.out.println("index!");
		return "index";
	}
	@RequestMapping("/landing")
	public String index() {
		System.out.println("landing!");
		return "landing";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		System.out.println("login get!");
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login() {
		System.out.println("login post!");
		return "login";
	}
	@RequestMapping(value ="/logoff",method = RequestMethod.GET)
	public String logoff() {
		System.out.println("logoff GET!");
		return "logoff";
	}
	@RequestMapping(value ="/signup",method = RequestMethod.GET)
	public String signupview() {
		System.out.println("signup GET!");
		return "signup";
	}
	@RequestMapping("/booking")
	public String booking() {
		System.out.println("booking!");
		return "booking";
	}
	@RequestMapping("/samples")
	public String sample() {
		System.out.println("samples!");
		return "samples";
	}
	@RequestMapping("/verify-email")
	public String validateEmail() {
		System.out.println("verify-email view!");
		return "email_validation";
	}
	/*@RequestMapping("home")
	public String home() {
		System.out.println("home!");
		return "home";
	}*/

}
