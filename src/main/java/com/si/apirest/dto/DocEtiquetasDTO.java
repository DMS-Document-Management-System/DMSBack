package com.si.apirest.dto;

import java.util.List;

import lombok.Data;

@Data
public class DocEtiquetasDTO {
    private int id;
    private String titulo;
    private String descripcion;
    private String archivoUrl;
    private List<EtiquetaReturnDTO> etiquetas;
}
