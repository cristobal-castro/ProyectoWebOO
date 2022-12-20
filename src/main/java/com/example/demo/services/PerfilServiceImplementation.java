package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UsuarioRepository;

@Service
public class PerfilServiceImplementation {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void setUser(String name) {
        // TODO Auto-generated method stub
        
    }

}
