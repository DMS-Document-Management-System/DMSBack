package com.si.apirest.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.Filter;

@Entity
@Data
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String archivoUrl;

    private LocalDateTime fechaVersion;

    @ManyToOne
    private Documento documento;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = true)
    private Tenant tenant;

}
