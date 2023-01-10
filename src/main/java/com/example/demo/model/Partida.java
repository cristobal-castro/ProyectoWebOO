package com.example.demo.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="reserva_usuario")
public class Partida {
       
    @EmbeddedId
    private PartidaKey id;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    
    @ManyToOne
    @MapsId("idReserva")
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;


    public PartidaKey getId() {
        return id;
    }


    public void setId(PartidaKey id) {
        this.id = id;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Reserva getReserva() {
        return reserva;
    }


    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    
}
