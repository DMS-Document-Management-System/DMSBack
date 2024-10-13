package com.si.apirest.dto.Paciente;

import lombok.Data;

@Data
public class PacienteDTO {
    private int id;

    private String dni;

    private String nombre;
    private String apellido;
}
