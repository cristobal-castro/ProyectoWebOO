package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cancha;
import com.example.demo.services.CanchaServiceImplementation;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cancha")
public class CanchaController {
	
	@Autowired
	CanchaServiceImplementation canchaService;
	
	
	@GetMapping("/VerCanchas")
	public String verCanchas(Model model) {
		model.addAttribute("canchas",canchaService.listAll());
		return "cancha/verCanchas";
	}
	@GetMapping("/Habilitar")
	public String Habilitar(@RequestParam Integer id) {
		Cancha cancha=canchaService.getById(id);
		cancha.setEstado("Habilitada");
		canchaService.saveCancha(cancha);
		return "redirect:VerCanchas";
	}
	@GetMapping("/Deshabilitar")
	public String Deshabilitar(@RequestParam Integer id) {
		Cancha cancha=canchaService.getById(id);
		cancha.setEstado("Deshabilitada");
		canchaService.saveCancha(cancha);
		return "redirect:VerCanchas";
	}
	@GetMapping("/Agregar")
	public String Agregar(Model model) {
		Cancha cancha=new Cancha();
		int cantidad=canchaService.listAll().size();
		model.addAttribute(cancha);
		model.addAttribute(cantidad);
		return "cancha/crearCancha";
	}
	@PostMapping("/save")
	public String save(Model model, @Valid Cancha cancha, BindingResult result) {
		
		if (result.hasErrors()) {
			Cancha cancha1=new Cancha();
			model.addAttribute(cancha1);
			return "redirect:Agregar";
		}
		cancha.setEstado("Habilitada");
		canchaService.saveCancha(cancha);
		return "redirect:VerCanchas";
	}
	
}
