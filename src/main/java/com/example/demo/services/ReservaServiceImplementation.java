package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
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
	public Reserva saveReserva(Reserva reserva) {
		try {
			System.out.println("GUARDAR RESERVAR");
			return reservaRepository.save(reserva);	
			
		} catch (Exception e) {
			System.out.println("No se pudo guardar la reserva: " + e.toString());
		}
		return null;

	}

	@Override
	public void deleteReserva(Integer id) {
		reservaRepository.deleteById(id);

	}

	@Override
	public Reserva getById(Integer id) {
		return reservaRepository.getById(id);
	}

	@Override
	public List<Reserva> filter(Date fecha) {
		// TODO Auto-generated method stub
		return reservaRepository.findByFecha(fecha);
	}

	@Override
	public List<Reserva> listByDate(String fecha) {
		return reservaRepository.findReservaByFecha(fecha);
	}
	
	public List<Reserva> partidasDisponibles(){
		List<Reserva>lista = reservaRepository.getPartidasDisponibles();
		return  lista.stream().filter(r -> r.getJugadores().size() < 4).toList();
		
	}

}
