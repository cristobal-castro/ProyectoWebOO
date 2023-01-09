package com.example.demo.model;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="reserva")
public class Reserva {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_reserva")
	private Integer idReserva;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	
	@Column(name="hora_inicio")
	@NotNull
	private String horaInicio;
	
	private String estado;
	private Integer nivel;
	private Integer categoria;
	private Integer tipo;

	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "id_cancha")
	private Cancha cancha;

	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cancha getCancha() {
		return cancha;
	}

	public void setCancha(Cancha cancha) {
		this.cancha = cancha;
	}
	

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", estado="
				+ estado + ", cancha=" + cancha + "]";
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
}
