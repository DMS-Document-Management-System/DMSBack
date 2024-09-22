package com.si.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.EtiquetaDTO;
import com.si.apirest.dto.EtiquetaDocs;
import com.si.apirest.dto.EtiquetaReturnDTO;
import com.si.apirest.projection.EtiquetaView;
import com.si.apirest.service.EtiquetaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/v1/etiquetas")
@RequiredArgsConstructor
@Tag(name = "Etiquetas")
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


    @DeleteMapping("/{id}")
    public void deleteEtiqueta(@PathVariable int id) {
        etiquetaService.deleteEtiqueta(id);
    }

    @PutMapping("/{id}")
    public EtiquetaReturnDTO updateEtiqueta(@PathVariable int id, @RequestBody EtiquetaDTO etiquetaDTO) {
        return etiquetaService.updateEtiqueta(id, etiquetaDTO);
    }
    

}
