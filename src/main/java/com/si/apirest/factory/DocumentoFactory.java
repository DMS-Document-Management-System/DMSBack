package com.si.apirest.factory;

import org.modelmapper.ModelMapper;

import com.si.apirest.dto.Documento.DocEtiquetasDTO;
import com.si.apirest.dto.Documento.DocumentoDTO;
import com.si.apirest.entity.Documento;

public class DocumentoFactory {

    public static DocEtiquetasDTO createDocEtiquetasDTO(Documento documento) {
        ModelMapper modelMapper = new ModelMapper();
        DocEtiquetasDTO docEtiquetasDTO = new DocEtiquetasDTO();
        modelMapper.map(documento, docEtiquetasDTO);
        System.out.println(docEtiquetasDTO);
        return docEtiquetasDTO;
    }

    public static DocumentoDTO createDocumentoDTO(Documento documento) {
        ModelMapper modelMapper = new ModelMapper();
        DocumentoDTO documentoDTO = new DocumentoDTO();
        modelMapper.map(documento, documentoDTO);
        System.out.println(documentoDTO);
        return documentoDTO;
    }

    public static Documento createDocumento(DocEtiquetasDTO docEtiquetasDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Documento documento = new Documento();
        modelMapper.map(docEtiquetasDTO, documento);
        System.out.println(documento);
        return documento;
    }

    public static Documento createDocumento(DocumentoDTO documentoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Documento documento = new Documento();
        modelMapper.map(documentoDTO, documento);
        System.out.println(documento);
        return documento;
    }

    

}
