package com.si.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.DocEtiquetaPostDTO;
import com.si.apirest.dto.DocEtiquetasDTO;
import com.si.apirest.dto.EtiquetaDocs;
import com.si.apirest.projection.EtiquetaView;
import com.si.apirest.service.DocumentoService;
import com.si.apirest.service.EtiquetaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Documentos y Etiquetas")
public class DocEtiquetaController {

    private final EtiquetaService etiquetaService;

    private final DocumentoService documentoService;


    
    @PutMapping("/documentos/etiquetas")
    public DocEtiquetasDTO addEtiquetaToDocument(@RequestBody DocEtiquetaPostDTO docEtiquetasDTO) {
        return documentoService.addEtiquetaToDocument(docEtiquetasDTO);
    }

    @PostMapping("/documentos/etiquetas")
    public DocEtiquetasDTO createDocumentoEtiquetas(@RequestBody DocEtiquetaPostDTO docEtiquetasDTO) {
        return documentoService.createDocumentoEtiquetas(docEtiquetasDTO);
    }


    @Operation(summary = "Obtener etiquetas por id de documento", description = "Devuelve una lista de etiquetas que contienen solo el nombre.")
    @GetMapping("/etiquetas/documento/{documentoId}")
    public List<EtiquetaView> getEtiquetasByDocumentoId(@PathVariable int documentoId) {
        return etiquetaService.getEtiquetasByDocumentoId(documentoId);
    }

    @Operation(summary = "Añadir un documento a una etiqueta", description = "Asocia un documento a una etiqueta específica.")
    @PostMapping("/etiquetas/{etiquetaId}/documentos/{documentoId}")
    public EtiquetaDocs addDocumentoToEtiqueta(@PathVariable int etiquetaId, @PathVariable int documentoId) {
        return etiquetaService.addDocumentoToEtiqueta(etiquetaId, documentoId);
    }

    @Operation(summary = "Eliminar un documento de una etiqueta", description = "Elimina un documento asociado a una etiqueta.") 
    @DeleteMapping("/etiquetas/{etiquetaId}/documentos/{documentoId}")
    public void removeDocumentFromEtiqueta(@PathVariable int etiquetaId, @PathVariable int documentoId) {
        etiquetaService.removeDocumentFromEtiqueta(etiquetaId, documentoId);
    }


}
