package com.example.demo.model;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.annotations.GenerationTime;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="noticia")
public class Noticia {

    @Id
    @NotNull
    @Column(name="id_noticia")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idNoticia;
    @NotEmpty
    @Column(name="titulo_noticia")
    private String tituloNoticia;
    @Column(name="descripcion_noticia")
    private String descripcionNoticia;
    @Column(name="fecha_inicio")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String fechaInicio;
    @NotEmpty
    @Column(name="fecha_fin")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String fechaFin;

    public Noticia(){

    }

    public Noticia(int idNoticia,String tituloNoticia, String descripcionNoticia, String fechaInicio, String fechaFin){
        this.idNoticia = idNoticia;
        this.tituloNoticia=tituloNoticia;
        this.descripcionNoticia=descripcionNoticia;
        this.fechaInicio=fechaInicio;
        this.fechaFin=this.fechaFin;
    }

    public int getIdNoticia() {
        return idNoticia;
    }
    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }
    public String getTituloNoticia() {
        return tituloNoticia;
    }
    public void setTituloNoticia(String tituloNoticia) {
        this.tituloNoticia = tituloNoticia;
    }
    public String getDescripcionNoticia() {
        return descripcionNoticia;
    }
    public void setDescripcionNoticia(String descripcionNoticia) {
        this.descripcionNoticia = descripcionNoticia;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaActual(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        setFechaInicio(format.format(date));
    }
}
