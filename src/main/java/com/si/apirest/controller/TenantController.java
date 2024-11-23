package com.si.apirest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Tenant.TenantRequest;
import com.si.apirest.service.TenantService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/tenant")
@RequiredArgsConstructor
@Tag(name = "Tenant")
public class TenantController {
    
    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<?> saveTenant(@RequestBody @Valid TenantRequest tenant) {
        tenantService.saveTenant(tenant);
        return ResponseEntity.ok().build();
    }

}
