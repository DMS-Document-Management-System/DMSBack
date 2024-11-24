package com.si.apirest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


import com.si.apirest.security.util.TenantEntityListener;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
@EqualsAndHashCode(callSuper=false)
@EntityListeners(TenantEntityListener.class)
public class Paciente extends BaseEntity{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dni;

    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "paciente")
    private List<Documento> documentos;


}
