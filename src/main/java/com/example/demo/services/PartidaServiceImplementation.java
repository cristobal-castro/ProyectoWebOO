package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Partida;
import com.example.demo.repository.PartidaRepository;

public class PartidaServiceImplementation  implements PartidaService{

    @Autowired
    private  PartidaRepository partidaRepository;

    @Override
    public List<Partida> listAll() {
        return partidaRepository.findAll();
    }

    @Override
    public void savePartida(Partida p) {
        // TODO Auto-generated method stub
        
    }
    
}
