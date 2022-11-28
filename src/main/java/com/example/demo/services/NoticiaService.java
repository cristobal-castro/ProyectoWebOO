package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Noticia;

public interface NoticiaService {
	List<Noticia> listAll();
	void saveCancha(Noticia noticia);
	void deleteCancha(Integer id);
	Noticia getById(Integer id);
}