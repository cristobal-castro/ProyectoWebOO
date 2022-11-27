package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
@Service
public class ReservaServiceImplementation implements ReservaService {
	
	@Autowired
	ReservaRepository reservaRepository;
	
	@Override
	public List<Reserva> listAll() {
		return reservaRepository.findAll();
	}

	@Override
	public void saveReserva(Reserva reserva) {
		reservaRepository.save(reserva);

	}

	@Override
	public void deleteReserva(Integer id) {
		reservaRepository.deleteById(id);

	}

	@Override
	public Reserva getById(Integer id) {
		return reservaRepository.getById(id);
	}

}
