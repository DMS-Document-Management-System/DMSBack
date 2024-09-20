package com.si.apirest.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.DocumentoDTO;
import com.si.apirest.dto.PacienteDTO;
import com.si.apirest.dto.PacienteDocDTO;
import com.si.apirest.entity.Paciente;
import com.si.apirest.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {
    @Autowired
    private final PacienteRepository pacienteRepository;

    private final ModelMapper modelMapper;

    public Paciente savePaciente(PacienteDTO paciente) {
        Paciente pacienteEntity = modelMapper.map(paciente, Paciente.class);
        return pacienteRepository.save(pacienteEntity);
    }

    public PacienteDocDTO getPaciente(int id) {
        
        Paciente paciente = pacienteRepository.findById(id).orElse(null);

        List<DocumentoDTO> documentoDTOs = paciente.getDocumentos().stream()
                .map(documento -> modelMapper.map(documento, DocumentoDTO.class)).toList();

        PacienteDocDTO pacienteDocDTO = new PacienteDocDTO();
        pacienteDocDTO.setApellido(paciente.getApellido());
        pacienteDocDTO.setNombre(paciente.getNombre());
        pacienteDocDTO.setDni(paciente.getDni());
        pacienteDocDTO.setDocumentos(documentoDTOs);
        pacienteDocDTO.setId(id);
        return pacienteDocDTO;
    }

    public void updatePaciente(PacienteDTO paciente) {
        Paciente pacienteEntity = modelMapper.map(paciente, Paciente.class);
        pacienteRepository.save(pacienteEntity);
    }

    public List<PacienteDTO> getPacientes() {
        return pacienteRepository.findAll().stream()
        .map(paciente -> modelMapper.map(paciente, PacienteDTO.class)).toList();
    }

    public void deletePaciente(int id) {
        pacienteRepository.deleteById(id);
    }

}
