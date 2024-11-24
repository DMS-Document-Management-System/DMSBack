package com.si.apirest.entity;

import java.util.List;

import com.si.apirest.security.util.TenantEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.GenerationType;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@EntityListeners(TenantEntityListener.class)
public class Etiqueta extends BaseEntity{
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


}
