package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Reserva;

public interface ReservaService {
	
	List<Reserva> listAll();
	void saveReserva(Reserva reserva);
	void deleteReserva(Integer id);
	Reserva getById(Integer id);
}
