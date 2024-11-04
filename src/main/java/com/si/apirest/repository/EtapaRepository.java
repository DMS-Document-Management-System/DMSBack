package com.si.apirest.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si.apirest.entity.Etapa;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Integer>{
    List<Etapa> findAllByWorkflowId(int workflowId, Sort sort);
}
