package com.estudo.filipe.estudo.MVC.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping("/")
	public String Hello() {
		return "Bem-vindo meu parceiro";
	}
}
