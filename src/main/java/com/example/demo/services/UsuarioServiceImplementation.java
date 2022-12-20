package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
@Service
public class UsuarioServiceImplementation implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@Override
	public List<Usuario> listAll() {
		
		return usuarioRepository.findAll();
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);

	}

	@Override
	public void deleteUsuario(Integer id) {
		usuarioRepository.deleteById(id);

	}

	@Override
	public Usuario getById(Integer id) {
		try {
			return usuarioRepository.getById(id);	
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	@Override
	public Usuario findByCorreo(String correo) {
		try {
			return usuarioRepository.findByCorreo(correo);	
		} catch (Exception e) {
			return null;
		}
		
	}

}
