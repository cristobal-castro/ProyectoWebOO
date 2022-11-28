package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reserva;
import com.example.demo.repository.CanchaRepository;
import com.example.demo.repository.ReservaRepository;
@Service
public class ReservaServiceImplementation implements ReservaService {
	
	@Autowired
	ReservaRepository reservaRepository;
	
	@Autowired
	CanchaRepository canchaRepository;
	
	@Override
	public List<Reserva> listAll() {
		try {
			return reservaRepository.findAll();	
		} catch (Exception e) {
			System.out.println("Error en listAll: " + e.toString());
		}
		return new ArrayList<Reserva>();
	}

	@Override
	public void saveReserva(Reserva reserva) {
		try {
			reservaRepository.save(reserva);	
		} catch (Exception e) {
			System.out.println("No se pudo guardar la reserva: " + e.toString());
		}
		

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
