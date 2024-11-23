package com.si.apirest.entity;

import org.hibernate.annotations.Filter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Etapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private int orden;

    private String accionRequerida;

    @ManyToOne
    private Workflow workflow;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = true)
    private Tenant tenant;

}
