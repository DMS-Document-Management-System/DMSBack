package com.si.apirest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Tenant.TenantRequest;
import com.si.apirest.entity.Tenant;
import com.si.apirest.security.auth.TenantService;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Crea un nuevo tenant", 
        description = "Crea un nuevo tenant y envia credenciales de usuario admin al correo electronico."
    )
    public ResponseEntity<?> saveTenant(@RequestBody @Valid TenantRequest tenant) {
        tenantService.createNewTenant(tenant);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Tenant findByEmail(@RequestParam String email) {
        return tenantService.findByEmail(email);
    }

}
