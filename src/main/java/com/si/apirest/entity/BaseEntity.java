package com.si.apirest.entity;
import org.hibernate.annotations.Filter;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "tenant_id", nullable = false, updatable = false)
    private Long tenantId;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}

