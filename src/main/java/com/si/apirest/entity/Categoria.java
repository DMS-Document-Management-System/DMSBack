package com.si.apirest.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

import org.hibernate.annotations.Filter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToMany
    @JsonIgnore
    private List<Documento> documento;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = true)
    private Tenant tenant;

}
