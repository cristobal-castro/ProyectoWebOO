package com.example.demo.model;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@JoinTable(
        name = "reserva_usuario",
        joinColumns = @JoinColumn(name = "id_reserva", nullable = false),
        inverseJoinColumns = @JoinColumn(name="id_usuario", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Usuario> juagadores;

	@Column(name="hora_inicio")
	@NotNull
	private String horaInicio;

	@Column()
	private String estado;

	@Column()
	private Integer nivel;

	@Column()
	private Integer categoria;

	@Column()
	private Integer tipo;

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

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

	public Reserva() {
	}

	public Reserva(Date fecha, @NotNull String horaInicio, String estado, Integer nivel, Integer categoria,
			Integer tipo, Cancha cancha, Usuario usuario) {
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.estado = estado;
		this.nivel = nivel;
		this.categoria = categoria;
		this.tipo = tipo;
		this.cancha = cancha;
		this.usuario = usuario;
	}

	public void setCancha(Cancha cancha) {
		this.cancha = cancha;
	}
	
	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", estado="
				+ estado + ", nivel=" + nivel + ", categoria=" + categoria + ", tipo=" + tipo + ", cancha=" + cancha
				+ ", usuario=" + usuario + "]";
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

	public List<Usuario> getJuagadores() {
		if(juagadores==null){
			return new ArrayList<Usuario>();
		}
		return juagadores;
	}

	public void setJuagadores(List<Usuario> juagadores) {
		this.juagadores = juagadores;
	}	
}
