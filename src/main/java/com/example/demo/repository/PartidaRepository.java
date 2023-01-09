package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Partida;
import com.example.demo.model.PartidaKey;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, PartidaKey>{
/*     List<Parking> findByOutIsNull();
    Parking findByCarAndOutIsNull(String plaque); */
}