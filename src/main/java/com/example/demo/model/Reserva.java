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
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fecha;
	
	@NotNull
	private String horaInicio;
	
	@NotNull
	private String estado;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "idCancha")
	private Cancha cancha;
	
	
	
}
