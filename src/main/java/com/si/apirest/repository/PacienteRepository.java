package com.si.apirest.repository;

import org.springframework.stereotype.Repository;

import com.si.apirest.entity.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
    
}
