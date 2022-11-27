package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Cancha;

public interface CanchaService {
	List<Cancha> listAll();
	void saveCancha(Cancha cancha);
	void deleteCancha(Integer id);
	Cancha getById(Integer id);
}
