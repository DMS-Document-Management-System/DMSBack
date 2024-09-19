package com.si.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.si.apirest.entity.Etiqueta;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer> {
    
}
