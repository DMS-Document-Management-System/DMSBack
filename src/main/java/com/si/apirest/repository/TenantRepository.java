package com.si.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si.apirest.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {

    Tenant findByName(String name);
    
}
