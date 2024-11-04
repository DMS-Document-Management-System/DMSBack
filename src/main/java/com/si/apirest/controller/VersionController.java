package com.si.apirest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Version.VersionDTO;
import com.si.apirest.projection.VersionView;
import com.si.apirest.service.VersionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/version")
@RequiredArgsConstructor
@Tag(name = "Version")
public class VersionController {
    
    private final VersionService versionService;

    @PostMapping
    @Operation(summary = "Crea una nueva version de un documento.", 
        description = "Crea una version de documento. **El valor fecha puede omitirse**.<br/>" +
            "La url que se envie en esta nueva versión se colocará en el documento.<br/>"+
            "La url del documento se colocará en una entidad versión.<br/>" +
            "SOLO NECESITA ENVIAR EL ID DEL DOCUMENTO Y LA URL DE LA NUEVA VERSION."

    )
    public VersionDTO createVersion(@RequestBody VersionDTO versionDTO) {
        return versionService.createVersion(versionDTO);
    }

    @GetMapping("/{documentoId}")
    @Operation(summary = "Obtiene las versiones de un documento.",
        description = "Obtiene todas las versiones de un documento por su id.<br/>" +
        "Las versiones obtenidas estarán ordenadas por fecha, desde la mas nueva a la más antigua."
    ) 
    public List<VersionView> findAllVersionByDocumentoId(@PathVariable int documentoId) {
        return versionService.findAllByDocumentoId(documentoId);
    }
}
