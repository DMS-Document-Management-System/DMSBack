package com.si.apirest.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.DocumentoDTO;
import com.si.apirest.dto.PersonDTO;
import com.si.apirest.entity.Documento;
import com.si.apirest.entity.Paciente;
import com.si.apirest.entity.Person;
import com.si.apirest.exceptions.NotFoundException;
import com.si.apirest.repository.DocumentoRepository;

import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    @Autowired
    private final DocumentoRepository documentoRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public DocumentoDTO getDocumento(int id) {
        Documento documento = documentoRepository.findById(id).orElse(null);
        if (documento == null) {
          throw new NotFoundException("Document id not found.");
        }
        PersonDTO personDTO = modelMapper.map(documento.getPerson(), PersonDTO.class);

        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setDescripcion(documento.getDescripcion());
        documentoDTO.setFechaCreacion(documento.getFechaCreacion());
        documentoDTO.setFechaModificacion(documento.getFechaModificacion());
        documentoDTO.setPerson(personDTO);
        documentoDTO.setArchivoUrl(documento.getArchivoUrl());
        documentoDTO.setId(id);
        return documentoDTO;
    }

    public DocumentoDTO createDocumento(DocumentoDTO documentoDTO) {
        Documento documento = new Documento();
        documento.setTitulo(documentoDTO.getTitulo());
        documento.setArchivoUrl(documentoDTO.getArchivoUrl());
        documento.setDescripcion(documentoDTO.getDescripcion());
        documento.setFechaCreacion(new GregorianCalendar());
        documento.setFechaModificacion(new GregorianCalendar());
        documento.setPerson(modelMapper.map(documentoDTO.getPerson(), Person.class));
        documento.setPaciente(modelMapper.map(documentoDTO.getPaciente(), Paciente.class));
        documento = documentoRepository.save(documento);
        return modelMapper.map(documento, DocumentoDTO.class);
    }

    public void deleteDocumento(int id) {
        documentoRepository.deleteById(id);
    }

    public List<DocumentoDTO> getDocumentos() {
        List<Documento> documentos = documentoRepository.findAll();
        return documentos.stream()
                .map(documento -> modelMapper.map(documento, DocumentoDTO.class))
                .collect(Collectors.toList());
    }

}
