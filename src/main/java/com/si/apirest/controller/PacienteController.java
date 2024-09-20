package com.si.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.PacienteDTO;
import com.si.apirest.dto.PacienteDocDTO;
import com.si.apirest.entity.Paciente;
import com.si.apirest.service.PacienteService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/v1/pacientes")
@RequiredArgsConstructor
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
    public PacienteDocDTO getPaciente(int id) {
        return pacienteService.getPaciente(id);
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(int id) {
        pacienteService.deletePaciente(id);
    }

    @PutMapping
    public void updatePaciente(@RequestBody PacienteDTO pacienteDTO) {
        pacienteService.updatePaciente(pacienteDTO);
    }

}
