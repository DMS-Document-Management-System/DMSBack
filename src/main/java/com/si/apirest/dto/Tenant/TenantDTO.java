package com.si.apirest.dto.Tenant;

import lombok.Data;

@Data
public class TenantDTO {
    private String name;
    private String status; // ACTIVE, INACTIVE, etc. 
    private String subscriptionType; // FREE_TRIAL, BASIC, etc.
    private String email;
}
