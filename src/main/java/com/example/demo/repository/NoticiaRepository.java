package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
    
}
