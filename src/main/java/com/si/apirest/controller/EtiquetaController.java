package com.si.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.EtiquetaDTO;
import com.si.apirest.entity.Etiqueta;
import com.si.apirest.service.EtiquetaService;

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

    @PostMapping
    public Etiqueta createEtiqueta(@RequestBody EtiquetaDTO etiquetaDTO) {
        return etiquetaService.createEtiqueta(etiquetaDTO);
    }

    @GetMapping("/{id}")
    public Etiqueta getEtiquetaById(int id) {
        return etiquetaService.getEtiquetaById(id);
    }

    @GetMapping
    public Iterable<Etiqueta> getAllEtiquetas() {
        return etiquetaService.getAllEtiquetas();
    }

    @PostMapping("/{etiquetaId}/documentos/{documentoId}")
    public Etiqueta addDocumentoToEtiqueta(int etiquetaId, int documentoId) {
        return etiquetaService.addDocumentoToEtiqueta(etiquetaId, documentoId);
    }

    @DeleteMapping("/{etiquetaId}/documentos/{documentoId}")
    public void removeDocumentFromEtiqueta(int etiquetaId, int documentoId) {
        etiquetaService.removeDocumentFromEtiqueta(etiquetaId, documentoId);
    }

    @PutMapping("/{etiquetaId}/documentos")
    public Etiqueta updateDocumentoOfEtiqueta(int etiquetaId, @RequestBody List<Integer> documentoIds) {
        return etiquetaService.updateDocumentoOfEtiqueta(etiquetaId, documentoIds);
    }

    @PostMapping("/{etiquetaId}/documentos")
    public Etiqueta addEtiquetaToDocument(int etiquetaId, @RequestBody List<Integer> documentoIds) {
        return etiquetaService.addEtiquetaToDocument(etiquetaId, documentoIds);
    }

    @DeleteMapping("/{id}")
    public void deleteEtiqueta(int id) {
        etiquetaService.deleteEtiqueta(id);
    }

}
