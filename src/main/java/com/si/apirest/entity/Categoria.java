package com.si.apirest.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.si.apirest.security.util.TenantEntityListener;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@EntityListeners(TenantEntityListener.class)
public class Categoria extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToMany
    @JsonIgnore
    private List<Documento> documento;


}
