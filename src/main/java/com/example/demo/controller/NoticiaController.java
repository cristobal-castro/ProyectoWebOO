package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Noticia;
import com.example.demo.model.Usuario;
import com.example.demo.services.NoticiaServiceImplementation;
import com.example.demo.services.UsuarioServiceImplementation;

@RequestMapping("/noticia")
@Controller
public class NoticiaController {
    @Autowired
    NoticiaServiceImplementation service;
	
	@Autowired
	UsuarioServiceImplementation usuarioService;

    @PostMapping("/addNoticia")
    public String create(@CookieValue(value = "id", defaultValue="0") String id,@CookieValue(value = "rol", defaultValue="0") String rol,Noticia noticia, Model model){
        model.addAttribute(noticia);
        service.saveNoticia(noticia);
        model.addAttribute("noticiaActive", "active");
        model.addAttribute("noticias",service.listAll());
        
		Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id));
		model.addAttribute("id", id);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);

        return "noticia/prueba";
    }

    @GetMapping({"","/"})
    public String formNoticia(@CookieValue(value = "id", defaultValue="0") String id,@CookieValue(value = "rol", defaultValue="0") String rol,Model model){
        Noticia noticia = new Noticia();
        model.addAttribute(noticia);
        model.addAttribute("noticiaActive", "active");
        
        Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id));
		model.addAttribute("id", id);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);

        return "noticia/formNoticia";
    }
    @GetMapping({"","/verNoticias"})
    public String verNoticia(@CookieValue(value = "id", defaultValue="0") String id,@CookieValue(value = "rol", defaultValue="0") String rol,Model model){
        
        model.addAttribute("noticias",service.listAll());
        model.addAttribute("noticiaActive", "active");
        
        Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id));
		model.addAttribute("id", id);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);

        return "noticia/prueba";
    }

}
