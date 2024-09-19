package com.si.apirest.dto;

import lombok.Data;

import java.util.GregorianCalendar;

@Data
public class DocumentoDTO {
    private int id;

    private String titulo;
    private String descripcion;
    private GregorianCalendar fechaCreacion;
    private GregorianCalendar fechaModificacion;

    private String archivoUrl;

    private PersonDTO person;

    private PacienteDTO paciente;
}
