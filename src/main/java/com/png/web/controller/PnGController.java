	package com.png.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PnGController {
	@RequestMapping("/")
	public String milestone() {
		System.out.println("index!");
		return "/views/index";
	}
	@RequestMapping("landing")
	public String index() {
		System.out.println("landing!");
		return "/views/landing";
	}
	@RequestMapping("login")
	public String login() {
		System.out.println("login!");
		return "login";
	}
	@RequestMapping("signup")
	public String signup() {
		System.out.println("signup!");
		return "signup";
	}
	@RequestMapping("booking")
	public String booking() {
		System.out.println("booking!");
		return "booking";
	}
	@RequestMapping("samples")
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
