	package com.png;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PnGController {
	@RequestMapping("/")
	public String milestone() {
		System.out.println("milestone!");
		return "milestone";
	}
	@RequestMapping("index")
	public String index() {
		System.out.println("milestone!");
		return "index";
	}
	@RequestMapping("home")
	public String home() {
		System.out.println("home!");
		return "home";
	}

}