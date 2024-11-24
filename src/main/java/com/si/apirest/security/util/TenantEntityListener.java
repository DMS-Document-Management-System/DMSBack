package com.si.apirest.security.util;
import com.si.apirest.entity.BaseEntity;
import com.si.apirest.utils.TenantContext;

import jakarta.persistence.PrePersist;

public class TenantEntityListener {

    @PrePersist
    public void setTenantId(BaseEntity entity) {
        if (TenantContext.getCurrentTenant() != null) {
            Long tenantId = Long.valueOf(TenantContext.getCurrentTenant());
            if (tenantId == null) {
                throw new IllegalStateException("Tenant ID is not set in TenantContext!");
            }
            entity.setTenantId(tenantId);
        }
    }
}
