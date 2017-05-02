	package com.png;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PnGController {
	@RequestMapping("/")
	public String milestone() {
		System.out.println("index!");
		return "index";
	}
	@RequestMapping("landing")
	public String index() {
		System.out.println("landing!");
		return "landing";
	}
	/*@RequestMapping("home")
	public String home() {
		System.out.println("home!");
		return "home";
	}*/

}
