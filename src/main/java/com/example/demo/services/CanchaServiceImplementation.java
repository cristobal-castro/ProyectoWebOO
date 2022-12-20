package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cancha;
import com.example.demo.repository.CanchaRepository;
@Service
public class CanchaServiceImplementation implements CanchaService {

	@Autowired
	CanchaRepository canchaRepository;
	
	@Override
	public List<Cancha> listAll() {
		return canchaRepository.findAll();
	}

	@Override
	public void saveCancha(Cancha cancha) {
		canchaRepository.save(cancha);

	}

	@Override
	public void deleteCancha(Integer id) {
		canchaRepository.deleteById(id);
	}

	@Override
	public Cancha getById(Integer id) {
		
		return canchaRepository.getById(id);
	}

	@Override
	public List<Cancha> listHabilitadas(String estado) {
		// TODO Auto-generated method stub
		return canchaRepository.findByEstado(estado);
	}

}
