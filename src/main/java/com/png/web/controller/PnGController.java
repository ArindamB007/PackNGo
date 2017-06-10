package com.png.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@RequestMapping("/signup")
	public String signup() {
		System.out.println("signup!");
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
	/*@RequestMapping("home")
	public String home() {
		System.out.println("home!");
		return "home";
	}*/

}
