	package com.png;

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
	/*@RequestMapping("home")
	public String home() {
		System.out.println("home!");
		return "home";
	}*/

}
