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
    private Usuario place;

    
    @ManyToOne
    @MapsId("idReserva")
    @JoinColumn(name = "id_reserva")
    private Reserva car;


    public PartidaKey getId() {
        return id;
    }


    public void setId(PartidaKey id) {
        this.id = id;
    }


    public Usuario getPlace() {
        return place;
    }


    public void setPlace(Usuario place) {
        this.place = place;
    }


    public Reserva getCar() {
        return car;
    }


    public void setCar(Reserva car) {
        this.car = car;
    }
    
}
