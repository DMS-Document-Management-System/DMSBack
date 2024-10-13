package com.si.apirest.dto.Paciente;
import java.util.List;

import com.si.apirest.dto.Documento.DocumentoDTO;

import lombok.Data;

@Data
public class PacienteDocDTO {

    private int id;

    private String dni;

    private String nombre;
    private String apellido;

    private List<DocumentoDTO> documentos;
}


