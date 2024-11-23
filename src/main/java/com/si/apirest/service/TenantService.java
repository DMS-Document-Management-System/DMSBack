package com.si.apirest.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.si.apirest.dto.Tenant.TenantRequest;
import com.si.apirest.entity.Tenant;
import com.si.apirest.factory.TenantFactory;
import com.si.apirest.repository.TenantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {
    private final TenantRepository tenantRepository;

    public void saveTenant(TenantRequest tenantRequest) {
        Tenant tenantFound = tenantRepository.findByName(tenantRequest.getName());
        if (tenantFound != null) {
            throw new RuntimeException("Tenant already exists");
        }
        Tenant tenant = TenantFactory.toTenant(tenantRequest);
        tenant.setCreatedAt(LocalDateTime.now());
        tenantRepository.save(tenant);
    }


}
