package com.si.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.DocumentoDTO;
import com.si.apirest.dto.PacienteDTO;
import com.si.apirest.dto.PacienteDocDTO;
import com.si.apirest.entity.Paciente;
import com.si.apirest.service.PacienteService;

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
@RequestMapping("/v1/pacientes")
@RequiredArgsConstructor
@Tag(name = "Paciente")
public class PacienteController {
    
    private final PacienteService pacienteService;

    @PostMapping
    public Paciente createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        return pacienteService.savePaciente(pacienteDTO);
    }

    @GetMapping
    public List<PacienteDTO> getPacientes() {
        return pacienteService.getPacientes();
    }

    @GetMapping("/{id}")
    public PacienteDocDTO getPaciente(@PathVariable int id) {
        return pacienteService.getPaciente(id);
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable int id) {
        pacienteService.deletePaciente(id);
    }

    @PutMapping
    public void updatePaciente(@RequestBody PacienteDTO pacienteDTO) {
        pacienteService.updatePaciente(pacienteDTO);
    }

    @Operation(summary = "Documentos del paciente", description = "Dado un id de paciente obtiene una Lista de los documentos asociados a él.")
    @GetMapping("/{idPaciente}")
    public List<DocumentoDTO> getDocumentosByPaciente(@PathVariable int idPaciente) {
        return pacienteService.getDocumentosByPaciente(idPaciente);
    }

}
