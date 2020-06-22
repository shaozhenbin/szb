package com.szb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

	@GetMapping("/")
	public String root() {
		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

}
