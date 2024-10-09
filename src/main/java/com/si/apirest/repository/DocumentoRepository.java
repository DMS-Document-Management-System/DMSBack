package com.si.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si.apirest.entity.Categoria;
import com.si.apirest.entity.Documento;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer>{
    List<Categoria> findByPacienteId(int idPaciente);

    List<Documento> findByPacienteIdAndCategoriaId(int idPaciente, int idCategoria);
}
