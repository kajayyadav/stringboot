package com.a2y.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class TempleteController {

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/cources")
	public String getCources() {
		return "cources";
	}
}
