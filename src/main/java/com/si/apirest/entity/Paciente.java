package com.si.apirest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.util.List;

import org.hibernate.annotations.Filter;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Paciente {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dni;

    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "paciente")
    private List<Documento> documentos;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = true)
    private Tenant tenant;

}
