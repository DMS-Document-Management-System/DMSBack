package com.si.apirest.dto.Version;

import java.time.LocalDateTime;

import com.si.apirest.dto.Documento.DocEtiquetasDTO;
import com.si.apirest.projection.ForeignKey;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VersionDTO {
    private int id;

    
    private String archivoUrl;

    private LocalDateTime fechaVersion;

    @Schema(implementation = ForeignKey.class)
    private DocEtiquetasDTO documento;

}
