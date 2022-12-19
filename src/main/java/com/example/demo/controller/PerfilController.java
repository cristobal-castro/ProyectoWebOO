package com.example.demo.controller;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Usuario;
import com.example.demo.services.PerfilServiceImplementation;
import com.example.demo.services.UsuarioServiceImplementation;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private UsuarioServiceImplementation usuarioServiceImplementation;

    @GetMapping("")
    public String perfil( @CookieValue(value = "id", defaultValue = "-1") int idUser, Model model) {
        System.out.println("COOKIE ID:" + idUser);
        if(idUser<0) return "error";
        Usuario user = usuarioServiceImplementation.getById(idUser);
        model.addAttribute("usuario", user);
        return "perfil/perfil";
    }
    
    @PostMapping("/save")
    public String save( Model model,@Valid Usuario user) {
        usuarioServiceImplementation.saveUsuario(user);
        System.out.println("ID:" + user.getId());
        model.addAttribute("usuario", user);
        return "redirect:/perfil";
    }

}
