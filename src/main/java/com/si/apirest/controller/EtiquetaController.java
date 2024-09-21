package com.si.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.EtiquetaDTO;
import com.si.apirest.dto.EtiquetaDocs;
import com.si.apirest.dto.EtiquetaReturnDTO;
import com.si.apirest.projection.EtiquetaView;
import com.si.apirest.service.EtiquetaService;

import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public EtiquetaReturnDTO createEtiqueta(@RequestBody EtiquetaDTO etiquetaDTO) {
        return etiquetaService.createEtiqueta(etiquetaDTO);
    }

    @Operation(summary = "Obtener una etiqueta por ID", description = "Devuelve la información de una etiqueta específica según el ID proporcionado.")
    @GetMapping("/{id}")
    public EtiquetaDocs getEtiquetaById(@PathVariable int id) {
        return etiquetaService.getEtiquetaById(id);
    }

    @Operation(summary = "Obtener todas las etiquetas", description = "Devuelve una lista de todas las etiquetas.")
    @GetMapping
    public Iterable<EtiquetaView> getAllEtiquetas() {
        return etiquetaService.getAllEtiquetas();
    }

    @Operation(summary = "Añadir un documento a una etiqueta", description = "Asocia un documento a una etiqueta específica.")
    @PostMapping("/{etiquetaId}/documentos/{documentoId}")
    public EtiquetaDocs addDocumentoToEtiqueta(@PathVariable int etiquetaId, @PathVariable int documentoId) {
        return etiquetaService.addDocumentoToEtiqueta(etiquetaId, documentoId);
    }

    @Operation(summary = "Eliminar un documento de una etiqueta", description = "Elimina un documento asociado a una etiqueta.") 
    @DeleteMapping("/{etiquetaId}/documentos/{documentoId}")
    public void removeDocumentFromEtiqueta(@PathVariable int etiquetaId, @PathVariable int documentoId) {
        etiquetaService.removeDocumentFromEtiqueta(etiquetaId, documentoId);
    }

    @DeleteMapping("/{id}")
    public void deleteEtiqueta(@PathVariable int id) {
        etiquetaService.deleteEtiqueta(id);
    }

    @Operation(summary = "Obtener etiquetas por id de documento", description = "Devuelve una lista de etiquetas que contienen solo el nombre.")
    @GetMapping("/documento/{documentoId}")
    public List<EtiquetaView> getEtiquetasByDocumentoId(@PathVariable int documentoId) {
        return etiquetaService.getEtiquetasByDocumentoId(documentoId);
    }

    @PutMapping("/{id}")
    public EtiquetaReturnDTO updateEtiqueta(@PathVariable int id, @RequestBody EtiquetaDTO etiquetaDTO) {
        return etiquetaService.updateEtiqueta(id, etiquetaDTO);
    }
    

}
