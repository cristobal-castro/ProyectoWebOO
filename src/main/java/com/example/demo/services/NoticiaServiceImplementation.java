package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Noticia;
import com.example.demo.repository.NoticiaRepository;

@Service
public class NoticiaServiceImplementation {
    @Autowired
    NoticiaRepository repository;
    
    public List<Noticia> listAll(){
        return repository.findAll();
    }

    public void saveNoticia(Noticia noticia){
        repository.save(noticia);
    }

    public void deleteNoticia(Integer id){
        repository.deleteById(id);
    }

    public Noticia getById(Integer id){
        return repository.getById(id);
    }
}
