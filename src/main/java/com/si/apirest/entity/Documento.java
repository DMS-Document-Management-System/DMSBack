package com.si.apirest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.annotations.Filter;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Data
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String descripcion;

    @Column(name = "fecha_creacion")
    private GregorianCalendar fechaCreacion;
    @Column(name = "fecha_modificacion")
    private GregorianCalendar fechaModificacion;

    private String archivoUrl;

    @ManyToOne
    private Person person;

    @ManyToOne
    @JsonIgnore
    private Paciente paciente;

    @ManyToMany(mappedBy = "documentos")
    @JsonIgnore
    private List<Etiqueta> etiquetas;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = true)
    private Tenant tenant;

}
