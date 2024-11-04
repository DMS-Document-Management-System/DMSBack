package com.si.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si.apirest.entity.Workflow;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Integer>{
    
}
