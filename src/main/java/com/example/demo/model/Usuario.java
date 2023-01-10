package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;

	@Column(name = "rut")
	@NotNull
	private String rut;

	@Column(name = "nombre")
	@NotNull
	private String nombre;

	@Column(name = "apellido")
	@NotNull
	private String apellido;

	@Column(name = "genero")
	@NotNull
	private String genero;

	@Column(name = "correo")
	@NotNull
	@Email
	private String correo;

	@Column(name = "password")
	@NotNull
	private String password;

	@Column(name = "rol")
	private Integer rol;

	@Column(name = "estado")
	private String estado;

	@ManyToMany(mappedBy = "jugadores")
	private List<Reserva> partidas;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
	private List<Reserva> reservas;

	public Usuario() {
	}

	public Usuario(Integer id, @NotNull String rut, @NotNull String nombre, @NotNull String apellido,
			@NotNull String genero, @NotNull @Email String correo, @NotNull String password, Integer rol,
			String estado) {
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.correo = correo;
		this.password = password;
		this.rol = rol;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public List<Reserva> getReservas() {
		if(reservas==null) return new ArrayList<Reserva>();
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void addReservas(Reserva reserva) {
		this.reservas.add(reserva);
	}

	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", rut=" + rut + ", nombre=" + nombre + ", apellido=" + apellido + ", genero="
				+ genero + ", correo=" + correo + ", password=" + password + ", rol=" + rol + ", estado=" + estado
				+ "]";
	}

	public List<Reserva> getPartidas() {
		if(partidas==null) {
			partidas= new ArrayList<>();
		}
		return partidas;
	}

	public void addPartidas(Reserva partida) {
		if(partidas==null) {
			partidas= new ArrayList<>();
		}
		partidas.add(partida);
	}

	public void setPartidas(List<Reserva> partidas) {
		this.partidas = partidas;
	}

}
