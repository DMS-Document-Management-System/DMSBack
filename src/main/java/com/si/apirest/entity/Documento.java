package com.si.apirest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.GregorianCalendar;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.si.apirest.security.util.TenantEntityListener;


@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@EntityListeners(TenantEntityListener.class)
public class Documento extends BaseEntity{

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


}
