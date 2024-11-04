package com.si.apirest.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String archivoUrl;

    private LocalDateTime fechaVersion;

    @ManyToOne
    private Documento documento;

}
