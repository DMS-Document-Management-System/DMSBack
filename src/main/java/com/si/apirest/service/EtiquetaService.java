package com.si.apirest.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.EtiquetaDTO;
import com.si.apirest.dto.EtiquetaDocs;
import com.si.apirest.dto.EtiquetaReturnDTO;
import com.si.apirest.entity.Documento;
import com.si.apirest.entity.Etiqueta;
import com.si.apirest.exceptions.NotFoundException;
import com.si.apirest.projection.EtiquetaView;
import com.si.apirest.repository.DocumentoRepository;
import com.si.apirest.repository.EtiquetaRepository;


import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;
    
    private final DocumentoRepository documentoRepository;
    
    private final ModelMapper modelMapper;

    public EtiquetaReturnDTO createEtiqueta(EtiquetaDTO etiquetaDTO) {
        Etiqueta etiqueta = modelMapper.map(etiquetaDTO, Etiqueta.class);
        return modelMapper.map(etiquetaRepository.save(etiqueta), EtiquetaReturnDTO.class);
    }

    public EtiquetaDocs getEtiquetaById(int id) {
        Etiqueta etiqueta = etiquetaRepository.findById(id).orElseThrow(()->new NotFoundException("Etiqueta not found."));
        return modelMapper.map(etiqueta, EtiquetaDocs.class);
    }

    public Iterable<EtiquetaView> getAllEtiquetas() {
        return etiquetaRepository.findAllProjectedBy();
    }

    public EtiquetaDocs addDocumentoToEtiqueta(int etiquetaId, int documentoId) {
        Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId).orElseThrow(
            () -> new NotFoundException("Etiqueta not found.")
        );
        Documento documento = documentoRepository.findById(documentoId).orElseThrow(
            () -> new NotFoundException("documento not found.")
        );
        etiqueta.getDocumentos().add(documento);
        return modelMapper.map(etiquetaRepository.save(etiqueta), EtiquetaDocs.class);
    }

    public void removeDocumentFromEtiqueta(int etiquetaId, int documentoId) {
        Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId).orElseThrow(
            () -> new NotFoundException("Etiqueta not found.")
        );
        Documento documento = documentoRepository.findById(documentoId).orElseThrow(
            () -> new NotFoundException("documento not found.")
        );
        etiqueta.getDocumentos().remove(documento);
    }

    public void deleteEtiqueta(int id) {
        etiquetaRepository.deleteById(id);
    }

    public EtiquetaReturnDTO updateEtiqueta(int id, EtiquetaDTO etiquetaDTO) {
        Etiqueta existingEtiqueta = etiquetaRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Etiqueta not found.")
        );
        existingEtiqueta.setNombre(etiquetaDTO.getNombre());
        return modelMapper.map(etiquetaRepository.save(existingEtiqueta), EtiquetaReturnDTO.class);
    }

    public List<EtiquetaView> getEtiquetasByDocumentoId(int documentoId) {
        return etiquetaRepository.findByDocumentosId(documentoId);
    }



}
