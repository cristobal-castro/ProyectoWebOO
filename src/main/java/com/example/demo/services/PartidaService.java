package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Partida;
import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;

public interface PartidaService {

    List<Partida> listAll();
	void savePartida(Usuario idUsuario, Reserva IdReserva);
}