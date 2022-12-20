package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.Usuario;
import com.example.demo.services.UsuarioServiceImplementation;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private UsuarioServiceImplementation usuarioService;

    @GetMapping("")
    public String perfil(  
        @CookieValue(value = "id", defaultValue="0") String id_cookie,
        @CookieValue(value = "rol", defaultValue="0") String rol,
        Model model) {

            Usuario usuario = new Usuario();
            usuario = usuarioService.getById(Integer.parseInt(id_cookie));
            model.addAttribute("id", id_cookie);
            model.addAttribute("rol", rol);
            model.addAttribute("usuario", usuario);
        return "perfil/perfil";
    }
    
    @PostMapping("/save")
    public String save( 
        @CookieValue(value = "id", defaultValue="0") String id_cookie,
        @CookieValue(value = "rol", defaultValue="0") String rol,
        Model model,
        @Valid Usuario user) {

        usuarioService.saveUsuario(user);
        Usuario usuario = new Usuario();
        usuario = usuarioService.getById(Integer.parseInt(id_cookie));
        model.addAttribute("id", id_cookie);
        model.addAttribute("rol", rol);
        model.addAttribute("usuario", usuario);
        return "redirect:/perfil";
    }

}
