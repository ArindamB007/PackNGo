package com.png.web.controller;

import com.png.data.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PnGController {
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
		System.out.println("login!");
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login() {
		System.out.println("login!");
		return "login";
	}
	@RequestMapping(value ="/signup",method = RequestMethod.GET)
	public String signupview() {
		System.out.println("signup GET!");
		return "signup";
	}
	@RequestMapping(value ="/signup",method = RequestMethod.POST)
	@ResponseBody
	public User signup(@RequestBody User user) {
		System.out.println(user.getEmail());
		return user;
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
	/*@RequestMapping("home")
	public String home() {
		System.out.println("home!");
		return "home";
	}*/

}
