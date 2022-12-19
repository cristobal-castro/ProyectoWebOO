package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Usuario;
import com.example.demo.services.UsuarioServiceImplementation;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
	
	
	
	@Autowired
	UsuarioServiceImplementation usuarioService;

    @GetMapping("/")
	public String verCanchas(Model model) {
        model.addAttribute("homeActive", "active");
		return "home";
	}
    
    @GetMapping("/login")
    public String login(Model model) {
    	return "login";
    }
    @PostMapping("/verificar")
    public String verificar(Model model,@RequestParam String correo,@RequestParam String password,HttpServletResponse response) {
    	Usuario usuario=usuarioService.findByCorreo(correo);
    	if (usuario.getPassword().equals(password)) {
    		Cookie cookie=new Cookie("id",usuario.getId().toString());
        	response.addCookie(cookie);
        	cookie=new Cookie("rol", usuario.getRol().toString());
        	response.addCookie(cookie);
    		return "redirect:/";
    		
		}else {
			return "redirect:/login";
		}
    }
    
    @GetMapping("/registro")
    public String registro(Model model) {
    	Usuario usuario=new Usuario();
    	model.addAttribute("usuario",usuario);
    	return "registro";
    }
    
    @PostMapping("/save")
    public String save(Model model,Usuario usuario,HttpServletResponse response) {
    	usuario.setRol(2);
    	usuarioService.saveUsuario(usuario);
    	Cookie cookie=new Cookie("id", usuario.getId().toString());
    	response.addCookie(cookie);
    	cookie=new Cookie("rol", usuario.getRol().toString());
    	response.addCookie(cookie);
    	return "redirect:/";
    }
}
