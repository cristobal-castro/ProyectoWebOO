package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Partida;
import com.example.demo.model.PartidaKey;
import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PartidaRepository;
@Service
public class PartidaServiceImplementation  implements PartidaService{

    @Autowired
    private  PartidaRepository partidaRepository;

    @Override
    public List<Partida> listAll() {
        return partidaRepository.findAll();
    }

    @Override
    public void savePartida(Usuario usuario, Reserva reserva) {
        PartidaKey key = new PartidaKey();
        key.setIdReserva(reserva.getIdReserva());
        key.setIdUsuario(usuario.getId());
        Partida partida = new Partida();
        partida.setId(key);
        partida.setReserva(reserva);
        partida.setUsuario(usuario);
        partidaRepository.save(partida);
        
    }
    
}
