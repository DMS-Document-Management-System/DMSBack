package com.si.apirest.dto;

import lombok.Data;

import java.util.GregorianCalendar;

import com.si.apirest.projection.ForeignKey;

import io.swagger.v3.oas.annotations.media.Schema;


@Data
public class DocumentoDTO {
    private int id;

    @Schema(example = "titulo documento")
    private String titulo;
    @Schema(example = "una descripcion")
    private String descripcion;
    @Schema(hidden = true)
    private GregorianCalendar fechaCreacion;
    @Schema(hidden = true)
    private GregorianCalendar fechaModificacion;
    
    @Schema(example = "https://unaURL.com")
    private String archivoUrl;
    
    @Schema(implementation = ForeignKey.class)
    private PersonDTO person;
    
    @Schema(implementation = ForeignKey.class)
    private PacienteDTO paciente;

    @Schema(implementation = ForeignKey.class)
    private CategoriaDTO categoria;

    
    
}
