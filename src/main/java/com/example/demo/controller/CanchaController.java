package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cancha;
import com.example.demo.model.Usuario;
import com.example.demo.services.CanchaServiceImplementation;
import com.example.demo.services.UsuarioServiceImplementation;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cancha")
public class CanchaController {
	
	@Autowired
	CanchaServiceImplementation canchaService;

	@Autowired
	UsuarioServiceImplementation usuarioService;
	
	@GetMapping("/VerCanchas")
	public String verCanchas(@CookieValue(value = "id", defaultValue="0") String id,@CookieValue(value = "rol", defaultValue="0") String rol,Model model) {

		model.addAttribute("canchas",canchaService.listAll());
		model.addAttribute("canchaActive", "active");
		
		Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id));
		model.addAttribute("id", id);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);

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
	public String Agregar(@CookieValue(value = "id", defaultValue="0") String id,@CookieValue(value = "rol", defaultValue="0") String rol,Model model) {
		Cancha cancha=new Cancha();
		int cantidad=canchaService.listAll().size();
		model.addAttribute(cancha);
		model.addAttribute(cantidad);
		model.addAttribute("canchaActive", "active");

		Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id));
		model.addAttribute("id", id);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);
		
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
