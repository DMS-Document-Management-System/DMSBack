package com.si.apirest.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.DocEtiquetaPostDTO;
import com.si.apirest.dto.DocEtiquetasDTO;
import com.si.apirest.dto.DocumentoDTO;
import com.si.apirest.dto.EtiquetaReturnDTO;
import com.si.apirest.dto.PersonDTO;
import com.si.apirest.entity.Documento;
import com.si.apirest.entity.Etiqueta;
import com.si.apirest.entity.Paciente;
import com.si.apirest.entity.Person;
import com.si.apirest.exceptions.NotFoundException;
import com.si.apirest.repository.DocumentoRepository;
import com.si.apirest.repository.EtiquetaRepository;

import jakarta.transaction.Transactional;
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
    private final EtiquetaRepository etiquetaRepository;

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
        Documento documento = createDocumentos(documentoDTO);
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

    @Transactional
    public DocEtiquetasDTO addEtiquetaToDocument(DocEtiquetaPostDTO docEtiquetasDTO) {
        List<Etiqueta> etiquetas = docEtiquetasDTO.getEtiquetas().stream().map(
            etiquetaDTO -> modelMapper.map(etiquetaDTO, Etiqueta.class)
        ).collect(Collectors.toList());   

        Documento documento = documentoRepository.findById(docEtiquetasDTO.getId()).orElseThrow(
            ()-> new NotFoundException("Documento con ID no encontrada."));
        
            List<Etiqueta> etiquetasFind = etiquetaRepository.findAllByDocumentosId(documento.getId());

            etiquetasFind.forEach((etiqueta)->{
                System.out.println(etiqueta.getDocumentos().remove(documento)); 
            });
            
            etiquetas.forEach((etiqueta)->{
                if (etiqueta.getDocumentos() == null)
                    etiqueta.setDocumentos(new java.util.ArrayList<>());
                etiqueta.getDocumentos().add(documento);
            });

            etiquetaRepository.saveAll(etiquetas);

            return getDocumentoById(documento.getId());
    }

    @Transactional
    public DocEtiquetasDTO createDocumentoEtiquetas(DocEtiquetaPostDTO docEtiquetasDTO) {
        List<Etiqueta> etiquetas = docEtiquetasDTO.getEtiquetas().stream().map(
            etiquetaDTO -> modelMapper.map(etiquetaDTO, Etiqueta.class)
        ).collect(Collectors.toList());        

        Documento documento = createDocumentos(docEtiquetasDTO);
        documento.setPaciente(modelMapper.map(docEtiquetasDTO.getPaciente(), Paciente.class));
        documento.setPerson(modelMapper.map(docEtiquetasDTO.getPerson(), Person.class));
        Documento docSaved = documentoRepository.save(documento);

        etiquetas.forEach(etiqueta -> {
            if (etiqueta.getDocumentos() == null)
                etiqueta.setDocumentos(new java.util.ArrayList<>());
            etiqueta.getDocumentos().add(docSaved);
        });

        etiquetaRepository.saveAll(etiquetas);

        return docEtiquetasDTO;
    }

    public DocEtiquetasDTO getDocumentoById(int id) {
        Documento documento = documentoRepository.findById(id).orElseThrow(
            ()-> new NotFoundException("Documento con ID no encontrada."));
        List<EtiquetaReturnDTO> etiquetas = etiquetaRepository.findAllByDocumentosId(id).stream().map(
            etiqueta -> modelMapper.map(etiqueta, EtiquetaReturnDTO.class)
        ).collect(Collectors.toList());
        DocEtiquetasDTO docEtiquetasDTO = new DocEtiquetasDTO();
        docEtiquetasDTO.setEtiquetas(etiquetas);
        docEtiquetasDTO.setId(documento.getId());
        docEtiquetasDTO.setArchivoUrl(documento.getArchivoUrl());
        docEtiquetasDTO.setDescripcion(documento.getDescripcion());
        docEtiquetasDTO.setTitulo(documento.getTitulo());
        return docEtiquetasDTO;
    }

    

    public Documento createDocumentos(DocumentoDTO documentoDTO) {
        Documento documento = new Documento();
        documento.setTitulo(documentoDTO.getTitulo());
        documento.setArchivoUrl(documentoDTO.getArchivoUrl());
        documento.setDescripcion(documentoDTO.getDescripcion());
        documento.setFechaCreacion(new GregorianCalendar());
        documento.setFechaModificacion(new GregorianCalendar());
        return documento;
    }

    public Documento createDocumentos(DocEtiquetasDTO documentoDTO) {
        Documento documento = new Documento();
        documento.setTitulo(documentoDTO.getTitulo());
        documento.setArchivoUrl(documentoDTO.getArchivoUrl());
        documento.setDescripcion(documentoDTO.getDescripcion());
        documento.setFechaCreacion(new GregorianCalendar());
        documento.setFechaModificacion(new GregorianCalendar());
        return documento;
    }

}
