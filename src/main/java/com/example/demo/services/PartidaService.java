package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Partida;

public interface PartidaService {

    List<Partida> listAll();
	void savePartida(Partida p);
}