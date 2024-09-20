package com.si.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.EtiquetaDTO;
import com.si.apirest.entity.Etiqueta;
import com.si.apirest.service.EtiquetaService;

import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RestController
@RequestMapping("/v1/etiquetas")
@RequiredArgsConstructor
public class EtiquetaController {
    
    private final EtiquetaService etiquetaService;

    @Operation(summary = "Crear una nueva etiqueta", description = "Crea una nueva etiqueta con la información proporcionada.")
    @PostMapping
    public Etiqueta createEtiqueta(@RequestBody EtiquetaDTO etiquetaDTO) {
        return etiquetaService.createEtiqueta(etiquetaDTO);
    }

    @Operation(summary = "Obtener una etiqueta por ID", description = "Devuelve la información de una etiqueta específica según el ID proporcionado.")
    @GetMapping("/{id}")
    public Etiqueta getEtiquetaById(int id) {
        return etiquetaService.getEtiquetaById(id);
    }

    @Operation(summary = "Obtener todas las etiquetas", description = "Devuelve una lista de todas las etiquetas.")
    @GetMapping
    public Iterable<Etiqueta> getAllEtiquetas() {
        return etiquetaService.getAllEtiquetas();
    }

    @Operation(summary = "Añadir un documento a una etiqueta", description = "Asocia un documento a una etiqueta específica.")
    @PostMapping("/{etiquetaId}/documentos/{documentoId}")
    public Etiqueta addDocumentoToEtiqueta(int etiquetaId, int documentoId) {
        return etiquetaService.addDocumentoToEtiqueta(etiquetaId, documentoId);
    }

    @Operation(summary = "Eliminar un documento de una etiqueta", description = "Elimina un documento asociado a una etiqueta.") 
    @DeleteMapping("/{etiquetaId}/documentos/{documentoId}")
    public void removeDocumentFromEtiqueta(int etiquetaId, int documentoId) {
        etiquetaService.removeDocumentFromEtiqueta(etiquetaId, documentoId);
    }

    @Operation(summary = "Actualizar documentos de una etiqueta", description = "Actualiza la lista de documentos asociados a una etiqueta.")
    @PutMapping("/{etiquetaId}/documentos")
    public Etiqueta updateDocumentoOfEtiqueta(int etiquetaId, @RequestBody List<Integer> documentoIds) {
        return etiquetaService.updateDocumentoOfEtiqueta(etiquetaId, documentoIds);
    }

    @Operation(summary = "Añadir documentos a una etiqueta", description = "Añade múltiples documentos a una etiqueta específica.")
    @PostMapping("/{etiquetaId}/documentos")
    public Etiqueta addEtiquetaToDocument(int etiquetaId, @RequestBody List<Integer> documentoIds) {
        return etiquetaService.addEtiquetaToDocument(etiquetaId, documentoIds);
    }

    @DeleteMapping("/{id}")
    public void deleteEtiqueta(int id) {
        etiquetaService.deleteEtiqueta(id);
    }

}
