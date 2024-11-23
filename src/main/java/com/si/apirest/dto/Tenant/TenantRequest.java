package com.si.apirest.dto.Tenant;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TenantRequest {

    @Schema(description = "Nombre del inquilino", example = "Mi Empresa")
    private String name;

    @Schema(description = "Estado del inquilino", example = "ACTIVE", allowableValues = {"ACTIVE", "INACTIVE", "SUSPENDED", "DELETED"})
    @NotNull(message = "El estado del inquilino no puede ser nulo")
    private String status; // ACTIVE, INACTIVE, etc.
    
    @Schema(description = "Tipo de suscripción", example = "FREE_TRIAL", allowableValues = {"FREE_TRIAL", "PREMIUM"})
    @NotNull(message = "El estado del inquilino no puede ser nulo")
    private String subscriptionType; // FREE_TRIAL, BASIC, etc.
}
