package com.si.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Documento.DocEtiquetaPostDTO;
import com.si.apirest.dto.Documento.DocEtiquetasDTO;
import com.si.apirest.dto.Documento.DocEtiquetasId;
import com.si.apirest.dto.Documento.DocumentoDTO;
import com.si.apirest.dto.Etiqueta.EtiquetaDocs;
import com.si.apirest.projection.EtiquetaView;
import com.si.apirest.service.DocumentoService;
import com.si.apirest.service.EtiquetaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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


    @Operation(summary = "Reemplazar etiquetas de documento", description = "Elimina las anteriores etiquetas y reemplaza las etiquetas asociadas a un documento.\n Enviar la id del documento como indica el ejemplo",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Cuerpo de la solicitud para actualizar las etiquetas de un documento",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(
                                value = """
                                        {
                                          "id": 0,
                                          "etiquetas": [
                                            {
                                              "id": "1"
                                            }
                                          ]
                                        }
                                        """
                        )
                )
        )
    )
    @PutMapping("/documentos/etiquetas")
    public DocEtiquetasDTO addEtiquetaToDocument(@RequestBody DocEtiquetaPostDTO docEtiquetasDTO) {
        return documentoService.addEtiquetaToDocument(docEtiquetasDTO);
    }

    @Operation(summary = "Crear documento con etiquetas",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody( // Usamos el alias para Swagger
            description = "En el post el campo id no es necesario. Tiene 2 opciones: "+
            "\n1. Puede eliminar el campo \"id\": 0"+
            "\n2. Puede dejar el campo con el \"id\": 0"
            , required = true, 
            content = @Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = DocEtiquetasId.class))
        )
    )
    @PostMapping("/documentos/etiquetas")
    public DocEtiquetasDTO createDocumentoEtiquetas(@RequestBody DocEtiquetasId docEtiquetasDTO) {
        return documentoService.createDocumentoEtiquetas(docEtiquetasDTO);
    }


    @Operation(summary = "Obtener etiquetas por id de documento", description = "Devuelve una lista de etiquetas que contienen solo el nombre.")
    @GetMapping("/etiquetas/documento/{documentoId}")
    public List<EtiquetaView> getEtiquetasByDocumentoId(@PathVariable int documentoId) {
        return etiquetaService.getEtiquetasByDocumentoId(documentoId);
    }

    @Operation(summary = "Añadir una etiqueta a un documento", description = "Asocia una etiqueta específica a un documento específico.")
    @PostMapping("/etiquetas/{etiquetaId}/documentos/{documentoId}")
    public EtiquetaDocs addDocumentoToEtiqueta(@PathVariable int etiquetaId, @PathVariable int documentoId) {
        return etiquetaService.addDocumentoToEtiqueta(etiquetaId, documentoId);
    }

    @Operation(summary = "Eliminar una etiqueta de un documento", description = "Elimina un documento asociado a una etiqueta.") 
    @DeleteMapping("/etiquetas/{etiquetaId}/documentos/{documentoId}")
    public void removeDocumentFromEtiqueta(@PathVariable int etiquetaId, @PathVariable int documentoId) {
        etiquetaService.removeDocumentFromEtiqueta(etiquetaId, documentoId);
    }

    @Operation(summary = "Obtener documentos por etiqueta", description = "Devuelve una lista de documentos asociados a una etiqueta específica.")
    @GetMapping("/etiquetas/{etiquetaId}/documentos")
    public List<DocumentoDTO> findDocumentosByEtiqueta(@PathVariable int etiquetaId) {
        return documentoService.findDocumentosByEtiqueta(etiquetaId);
    }


}
