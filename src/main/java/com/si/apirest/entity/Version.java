package com.si.apirest.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.si.apirest.security.util.TenantEntityListener;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@EntityListeners(TenantEntityListener.class)
public class Version extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String archivoUrl;

    private LocalDateTime fechaVersion;

    @ManyToOne
    private Documento documento;


}
