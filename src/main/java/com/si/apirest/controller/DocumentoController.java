package com.si.apirest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Documento.DocEtiquetasDTO;
import com.si.apirest.dto.Documento.DocumentoBase;
import com.si.apirest.dto.Documento.DocumentoDTO;
import com.si.apirest.service.DocumentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@RestController
@RequestMapping("/v1/documentos") 
@RequiredArgsConstructor
@Tag(name = "Documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    @PostMapping
    @Operation(summary = "Crear documento",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody( 
            description = "En el post el campo id no es necesario. Tiene 2 opciones: "+
            "\n1. Puede eliminar el campo \"id\": 0"+
            "\n2. Puede dejar el campo con el \"id\": 0"
        )
    )
    public DocumentoDTO createDocumento(@RequestBody DocumentoDTO documentoDTO) {
        return documentoService.createDocumento(documentoDTO);
    }

    @PutMapping("/{idDocumento}")
    @Operation(summary = "Editar Documento")
    public DocumentoDTO updateDocumento(@PathVariable int idDocumento, @RequestBody DocumentoBase documentoDTO) {
        return documentoService.updateDocummDocumentoDTO(idDocumento, documentoDTO);
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

    @GetMapping("/categoria/{idCategoria}")
    @Operation(summary = "Obetener documentos por id de categoria")
    public List<DocumentoDTO> getDocumentoByCategoria(@PathVariable int idCategoria) {
        return documentoService.documentosByCategoria(idCategoria);
    }

    @GetMapping("/paciente/{idPaciente}")
    @Operation(summary = "Obetener documentos por id de paciente")
    public List<DocumentoDTO> getDocumentoByPaciente(@PathVariable int idPaciente) {
        return documentoService.documentosByPaciente(idPaciente);
    }

    @GetMapping("/paciente/{idPaciente}/categoria/{idCategoria}")
    @Operation(summary = "Obetener documentos por id de categoria y paciente")
    public List<DocEtiquetasDTO> getDocumentosByPacienteAndCategoria(@PathVariable int idPaciente, @PathVariable int idCategoria) {
        return documentoService.getDocumentosByPacienteAndCategoria(idPaciente, idCategoria);
    }



}
