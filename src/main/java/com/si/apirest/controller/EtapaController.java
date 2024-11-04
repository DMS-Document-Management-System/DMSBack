package com.si.apirest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Etapa.EtapaDTO;
import com.si.apirest.service.EtapaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/etapa")
@RequiredArgsConstructor
@Tag(name = "Etapa")
public class EtapaController {
    
    private final EtapaService etapaService;

    @PostMapping
    @Operation(summary = "Crea una etapa de workflow.",
        description = "**Crea una etapa de workflow.**<br/>" + 
        "- Se debe enviar el nombre de la etapa.<br/>" + 
        "- El orden se debe enviar el orden de las etapas o jerarquía de las etapas.<br/>" + 
        "**- Ejemplo de orden**<br/>" + 
        "1. Subida del Documento - Orden 1<br/>" + 
        "2. Revisión Inicial - Orden 2<br/>" + 
        "3. Aprobación Intermedia - Orden 3" 
    )
    public EtapaDTO createEtapaDTO(@RequestBody EtapaDTO etapaDTO) {
        return etapaService.createEtapa(etapaDTO);
    }

    @GetMapping("/{idWorkflow}")
    @Operation(summary = "Obtiene todas las etapas de un workflow id.",
        description = "Obtiene una lista de todas las etapas de un workflow. <br/>" + 
        "- La lista está ordenada por el campo orden de forma ascendente."
    )
    public List<EtapaDTO> findAllEtapas(@PathVariable int idWorkflow) {
        return etapaService.findAllEtapaDTO(idWorkflow);
    }

}
