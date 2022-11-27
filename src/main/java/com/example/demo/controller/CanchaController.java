package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.CanchaServiceImplementation;

@Controller
public class CanchaController {
	
	@Autowired
	CanchaServiceImplementation canchaService;
	
	@RequestMapping("/")
	public String prueba(Model model) {
		for (int i = 0; i < canchaService.listAll().size(); i++) {
			System.out.println(canchaService.listAll().get(i).toString());
		}
		return "prueba";
	}
}
