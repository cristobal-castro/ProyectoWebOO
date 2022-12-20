package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reserva;
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByFecha(Date fromDate);
    @Query(value="SELECT * FROM reserva WHERE fecha > ?1",nativeQuery=true)
    List<Reserva> findReservaByFecha(String fecha);
}
