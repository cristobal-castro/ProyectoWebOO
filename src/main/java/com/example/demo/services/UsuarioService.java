package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Usuario;

public interface UsuarioService {

	
	List<Usuario> listAll();
	void saveUsuario(Usuario usuario);
	void deleteUsuario(Integer id);
	Usuario getById(Integer id);
	Usuario findByCorreo(String correo);
}
