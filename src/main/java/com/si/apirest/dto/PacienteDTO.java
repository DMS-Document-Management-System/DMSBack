package com.si.apirest.dto;

import lombok.Data;

@Data
public class PacienteDTO {
    private int id;

    private String dni;

    private String nombre;
    private String apellido;
}
