package com.si.apirest.dto;
import java.util.List;

import lombok.Data;

@Data
public class PacienteDocDTO {

    private int id;

    private String dni;

    private String nombre;
    private String apellido;

    private List<DocumentoDTO> documentos;
}


