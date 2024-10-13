package com.si.apirest.dto.Documento;

import com.si.apirest.dto.Paciente.PacienteDTO;
import com.si.apirest.dto.Person.PersonDTO;
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
