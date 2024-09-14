package com.si.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si.apirest.entity.PermissionEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity,Integer>{
    PermissionEntity findByNombre(String nombre);
}
