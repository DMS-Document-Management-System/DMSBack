package com.si.apirest.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.Categoria.CategoriaDTO;
import com.si.apirest.dto.Documento.DocEtiquetaPostDTO;
import com.si.apirest.dto.Documento.DocEtiquetasDTO;
import com.si.apirest.dto.Documento.DocEtiquetasId;
import com.si.apirest.dto.Documento.DocumentoBase;
import com.si.apirest.dto.Documento.DocumentoDTO;
import com.si.apirest.dto.Etiqueta.EtiquetaReturnDTO;
import com.si.apirest.entity.Categoria;
import com.si.apirest.entity.Documento;
import com.si.apirest.entity.Etiqueta;
import com.si.apirest.entity.Paciente;
import com.si.apirest.entity.Person;
import com.si.apirest.exceptions.NotFoundException;
import com.si.apirest.factory.DocumentoFactory;
import com.si.apirest.repository.CategoriaRepository;
import com.si.apirest.repository.DocumentoRepository;
import com.si.apirest.repository.EtiquetaRepository;
import com.si.apirest.repository.PacienteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import java.util.ArrayList;
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
    private final CategoriaRepository categoriaRepository;

    @Autowired 
    private final PacienteRepository pacienteRepository;

    @Autowired
    private final ModelMapper modelMapper;


    public DocumentoDTO createDocumento(DocumentoDTO documentoDTO) {
        Documento documento = createDocumentos(documentoDTO);
        documento.setPerson(modelMapper.map(documentoDTO.getPerson(), Person.class));
        documento.setPaciente(modelMapper.map(documentoDTO.getPaciente(), Paciente.class));
        CategoriaDTO categoriaDTO = documentoDTO.getCategoria();
        if (categoriaDTO != null && categoriaRepository.findById(categoriaDTO.getId()).isPresent())
            documento.setCategoria(modelMapper.map(categoriaDTO, Categoria.class));
        documento = documentoRepository.save(documento);
        return modelMapper.map(documento, DocumentoDTO.class);
    }

    public DocumentoDTO updateDocummDocumentoDTO(int idDocumento, DocumentoBase documentoDTO) {
        Documento documento = documentoRepository.findById(idDocumento).orElseThrow(()-> new NotFoundException("Documento id: " + idDocumento + " not found."));
        documento.setDescripcion(documentoDTO.getDescripcion());
        documento.setTitulo(documentoDTO.getTitulo());
        documento.setFechaModificacion(new GregorianCalendar());
        documento = documentoRepository.save(documento);
        return modelMapper.map(documento, DocumentoDTO.class);
    }

    public List<DocumentoDTO> documentosByCategoria(int idCategoria) {
        categoriaRepository.findById(idCategoria).orElseThrow(()-> new NotFoundException("Categoria id: " + idCategoria + " not found."));
        List<Documento> documentos = documentoRepository.findByCategoriaId(idCategoria);
        return documentos.stream().map(documento -> 
            modelMapper.map(documento, DocumentoDTO.class)
        )
        .collect(Collectors.toList());
    }

    public List<DocumentoDTO> documentosByPaciente(int idPaciente) {
        pacienteRepository.findById(idPaciente).orElseThrow(()-> new NotFoundException("Paciente id: " + idPaciente + " not found."));
        List<Documento> documentos = documentoRepository.findByPacienteId(idPaciente);
        return documentos.stream().map(documento ->
            modelMapper.map(documento, DocumentoDTO.class)
        )
        .collect(Collectors.toList());
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
    public DocEtiquetasDTO createDocumentoEtiquetas(DocEtiquetasId docEtiquetasDTO) {
        List<Etiqueta> etiquetas = etiquetaRepository.findAllById(docEtiquetasDTO.getEtiquetas());

        Documento documento = createDocumentos(docEtiquetasDTO);
        documento.setPaciente(modelMapper.map(docEtiquetasDTO.getPaciente(), Paciente.class));
        documento.setPerson(modelMapper.map(docEtiquetasDTO.getPerson(), Person.class));
        documento.setCategoria(modelMapper.map(docEtiquetasDTO.getCategoria(), Categoria.class));
        Documento docSaved = documentoRepository.save(documento);

        etiquetas.forEach(etiqueta -> {
            if (etiqueta.getDocumentos() == null)
                etiqueta.setDocumentos(new java.util.ArrayList<>());
            etiqueta.getDocumentos().add(docSaved);
        });

        etiquetaRepository.saveAll(etiquetas);

        List<EtiquetaReturnDTO> etiquetaReturnDTOs = etiquetas.stream().map(etiqueta -> modelMapper.map(etiqueta, EtiquetaReturnDTO.class)).collect(Collectors.toList());

        DocEtiquetasDTO docEtiquetas = new DocEtiquetasDTO();

        docEtiquetas.setEtiquetas(etiquetaReturnDTOs);
        docEtiquetas.setArchivoUrl(docSaved.getArchivoUrl());
        docEtiquetas.setDescripcion(docSaved.getDescripcion());
        docEtiquetas.setTitulo(docSaved.getTitulo());
        docEtiquetas.setId(docSaved.getId());

        return docEtiquetas;
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

    
    public List<DocEtiquetasDTO> getDocumentosByPacienteAndCategoria(int idPaciente, int idCategoria) {
        List<DocEtiquetasDTO> docEtiquetasDTOs = new ArrayList<DocEtiquetasDTO>();
        List<Documento> documentos = documentoRepository.findByPacienteIdAndCategoriaId(idPaciente, idCategoria);
        for (Documento documento : documentos) {
            List<EtiquetaReturnDTO> etiquetas = documento.getEtiquetas()
            .stream()
            .map(
                etiqueta -> modelMapper.map(etiqueta, EtiquetaReturnDTO.class)
            )
            .collect(Collectors.toList());
            DocEtiquetasDTO docEtiquetasDTO = new DocEtiquetasDTO();
            docEtiquetasDTO.setEtiquetas(etiquetas);
            docEtiquetasDTO.setId(documento.getId());
            docEtiquetasDTO.setArchivoUrl(documento.getArchivoUrl());
            docEtiquetasDTO.setDescripcion(documento.getDescripcion());
            docEtiquetasDTO.setTitulo(documento.getTitulo());
            docEtiquetasDTOs.add(docEtiquetasDTO);
        }
        return docEtiquetasDTOs;
    }

    public List<DocumentoDTO> findDocumentosByEtiqueta(int idEtiqueta) {
        Etiqueta etiqueta = etiquetaRepository.findById(idEtiqueta).orElseThrow(
            ()-> new NotFoundException("Etiqueta con ID no encontrada."));
        List<DocumentoDTO> documento = etiqueta.getDocumentos().stream().map(DocumentoFactory::createDocumentoDTO).toList();
        return documento;
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
