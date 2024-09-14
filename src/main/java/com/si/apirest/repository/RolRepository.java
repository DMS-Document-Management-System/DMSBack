package com.si.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.si.apirest.entity.RoleEntity;

public interface RolRepository extends JpaRepository<RoleEntity, Integer>{
    
}
