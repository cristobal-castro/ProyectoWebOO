package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Usuario;
import com.example.demo.services.UsuarioServiceImplementation;


@RequestMapping("/usuario")
@Controller
public class UsuarioController {
    @Autowired
    private UsuarioServiceImplementation service;

    @GetMapping("/lista")
    public String listaUsuarios (@CookieValue(value = "id", defaultValue="0") String id,@CookieValue(value = "rol", defaultValue="0") String rol,Model model){
        List<Usuario> usuarios = service.listAll();
        model.addAttribute("usuarios", usuarios);
        Usuario usuario = new Usuario();
		usuario = service.getById(Integer.parseInt(id));
		model.addAttribute("id", id);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);
        return "usuarios/lista";
    }

    @GetMapping("/Deshabilitar")
    public String banear (@RequestParam Integer id){
        Usuario usuario = service.getById(id);
        usuario.setEstado("Deshabilitado");
        service.saveUsuario(usuario);
        return "redirect:lista";
    }
    @GetMapping("/Habilitar")
    public String desbanear (@RequestParam Integer id){
        Usuario usuario = service.getById(id);
        usuario.setEstado("Habilitado");
        service.saveUsuario(usuario);
        return "redirect:lista";
    }
}
