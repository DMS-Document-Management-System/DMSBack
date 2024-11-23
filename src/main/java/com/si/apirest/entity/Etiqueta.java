package com.si.apirest.entity;

import java.util.List;

import org.hibernate.annotations.Filter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import jakarta.persistence.GenerationType;

@Entity
@Data
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

 @ManyToMany
    @JoinTable(
        name = "documento_etiquetas",
        joinColumns = @JoinColumn(name = "etiqueta_id"),
        inverseJoinColumns = @JoinColumn(name = "documento_id")
    )
    private List<Documento> documentos;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = true)
    private Tenant tenant;

}
