package com.si.apirest.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.EtiquetaDTO;
import com.si.apirest.entity.Documento;
import com.si.apirest.entity.Etiqueta;
import com.si.apirest.exceptions.NotFoundException;
import com.si.apirest.repository.DocumentoRepository;
import com.si.apirest.repository.EtiquetaRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;
    
    private final DocumentoRepository documentoRepository;
    
    private final ModelMapper modelMapper;

    public Etiqueta createEtiqueta(EtiquetaDTO etiquetaDTO) {
        Etiqueta etiqueta = modelMapper.map(etiquetaDTO, Etiqueta.class);
        return etiquetaRepository.save(etiqueta);
    }

    public Etiqueta getEtiquetaById(int id) {
        return etiquetaRepository.findById(id).orElse(null);
    }

    public Iterable<Etiqueta> getAllEtiquetas() {
        return etiquetaRepository.findAll();
    }

    public Etiqueta addDocumentoToEtiqueta(int etiquetaId, int documentoId) {
        Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId).orElseThrow(
            () -> new NotFoundException("Etiqueta not found.")
        );
        Documento documento = documentoRepository.findById(documentoId).orElseThrow(
            () -> new NotFoundException("documento not found.")
        );
        etiqueta.getDocumentos().add(documento);
        return etiquetaRepository.save(etiqueta);
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

    @Transactional
    public Etiqueta addEtiquetaToDocument(int etiquetaId, List<Integer> documentoIds) {
        Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId).orElseThrow(
            () -> new NotFoundException("Etiqueta not found.")
        );
        List<Documento> documentos = documentoRepository.findAllById(documentoIds);
        etiqueta.getDocumentos().addAll(documentos);
        return etiquetaRepository.save(etiqueta);
    }

    @Transactional
    public Etiqueta updateDocumentoOfEtiqueta(int etiquetaId, List<Integer> documentoIds) {
        Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId).orElseThrow(
            () -> new NotFoundException("Etiqueta not found.")
        );
        etiqueta.getDocumentos().clear();
        List<Documento> documentos = documentoRepository.findAllById(documentoIds);
        etiqueta.getDocumentos().addAll(documentos);
        return etiquetaRepository.save(etiqueta);
    }

    public void deleteEtiqueta(int id) {
        etiquetaRepository.deleteById(id);
    }

}
