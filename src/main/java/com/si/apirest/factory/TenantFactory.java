package com.si.apirest.factory;

import org.modelmapper.ModelMapper;

import com.si.apirest.dto.Tenant.TenantRequest;
import com.si.apirest.entity.Tenant;

public class TenantFactory {
    
    public static Tenant toTenant(TenantRequest tenantRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tenantRequest, Tenant.class);
    }

    public static TenantRequest toTenantRequest(Tenant tenant) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tenant, TenantRequest.class);
    }

}
