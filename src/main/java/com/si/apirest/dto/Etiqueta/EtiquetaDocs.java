package com.si.apirest.dto.Etiqueta;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.si.apirest.dto.Documento.DocumentoDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class EtiquetaDocs extends EtiquetaReturnDTO{
    private List<DocumentoDTO> documentos;
}
