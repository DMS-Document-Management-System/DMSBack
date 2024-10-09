package com.si.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.si.apirest.entity.Categoria;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    @Query("SELECT DISTINCT d.categoria FROM Documento d WHERE d.paciente.id = :idPaciente")
    List<Categoria> findCategoriasByPacienteId(@Param("idPaciente") int idPaciente);

}
