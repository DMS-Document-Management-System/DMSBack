package com.si.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si.apirest.entity.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer>{
    
}
