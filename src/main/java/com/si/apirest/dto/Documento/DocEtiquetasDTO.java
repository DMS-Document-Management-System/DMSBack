package com.si.apirest.dto.Documento;

import java.util.List;

import com.si.apirest.dto.Etiqueta.EtiquetaReturnDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DocEtiquetasDTO {
    private int id;
    private String titulo;
    private String descripcion;
    private String archivoUrl;
    @Schema(example = "[\n{\"id\":\"1\"}\n]")
    private List<EtiquetaReturnDTO> etiquetas;
}
