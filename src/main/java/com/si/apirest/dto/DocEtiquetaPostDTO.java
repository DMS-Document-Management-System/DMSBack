package com.si.apirest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class DocEtiquetaPostDTO extends DocEtiquetasDTO{
    
    private PersonDTO person;

    private PacienteDTO paciente;
}
