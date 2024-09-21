package com.si.apirest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.DocEtiquetaPostDTO;
import com.si.apirest.dto.DocEtiquetasDTO;
import com.si.apirest.dto.DocumentoDTO;
import com.si.apirest.service.DocumentoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@RestController
@RequestMapping("/v1/documentos") 
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    @PostMapping
    public DocumentoDTO createDocumento(@RequestBody DocumentoDTO documentoDTO) {
        return documentoService.createDocumento(documentoDTO);
    }

    @GetMapping
    public List<DocumentoDTO> getDocumento() {
        return documentoService.getDocumentos();
    }

    @DeleteMapping("/{id}")
    public void deleteDocumento(@PathVariable int id) {
        documentoService.deleteDocumento(id);
    }

    @GetMapping("/{id}")
    public DocEtiquetasDTO getDocumentoById(@PathVariable int id) {
        return documentoService.getDocumentoById(id);
    }

    @PutMapping("/etiquetas")
    public DocEtiquetasDTO addEtiquetaToDocument(@RequestBody DocEtiquetaPostDTO docEtiquetasDTO) {
        return documentoService.addEtiquetaToDocument(docEtiquetasDTO);
    }

    @PostMapping("/etiquetas")
    public DocEtiquetasDTO createDocumentoEtiquetas(@RequestBody DocEtiquetaPostDTO docEtiquetasDTO) {
        return documentoService.createDocumentoEtiquetas(docEtiquetasDTO);
    }

}
