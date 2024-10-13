package com.si.apirest.dto.Documento;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
public class DocEtiquetasId extends DocumentoDTO{
    private List<Integer> etiquetas;
}
