package com.si.apirest.dto;

import com.si.apirest.projection.ForeignKey;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class DocEtiquetaPostDTO extends DocEtiquetasDTO{
    
    @Schema(implementation = ForeignKey.class)
    private PersonDTO person;
    
    @Schema(implementation = ForeignKey.class)
    private PacienteDTO paciente;
}
