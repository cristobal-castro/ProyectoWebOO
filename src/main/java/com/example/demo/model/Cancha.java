package com.example.demo.model;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="cancha")
public class Cancha {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@NotNull
	private String nombre;
	
	private String estado;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cancha")
	private List<Reserva> reservas;

	public Cancha(String nombre,String estado) {
		this.nombre = nombre;
		this.estado=estado;
	}

	public Cancha() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cancha [id=" + id + ", name=" + nombre + ", estado=" + estado + "]";
	}

	public List<Reserva> getReservas() {
		return reservas;
	}
	

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	
	
}
