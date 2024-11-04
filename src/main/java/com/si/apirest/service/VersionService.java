package com.si.apirest.service;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.Version.VersionDTO;
import com.si.apirest.entity.Documento;
import com.si.apirest.entity.Version;
import com.si.apirest.exceptions.NotFoundException;
import com.si.apirest.factory.VersionFactory;
import com.si.apirest.projection.VersionView;
import com.si.apirest.repository.DocumentoRepository;
import com.si.apirest.repository.VersionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VersionService {
    
    private final VersionRepository versionRepository;

    private final DocumentoRepository documentoRepository;


    public VersionDTO createVersion(VersionDTO versionDTO) {
        int documentoId = versionDTO.getDocumento().getId();
        Documento documento = documentoRepository.findById(documentoId).orElseThrow(
            () -> new NotFoundException("Version: Document not found.")
        );

        String oldUrl = documento.getArchivoUrl();
            
        documento.setArchivoUrl(versionDTO.getArchivoUrl());
        documento.setFechaModificacion(new GregorianCalendar());

        versionDTO.setFechaVersion(LocalDateTime.now());
        versionDTO.setArchivoUrl(oldUrl);

        Version newVersion = versionRepository.save(VersionFactory.createVersion(versionDTO));
        return VersionFactory.createVersionDTO(newVersion);
    }

    public List<VersionView> findAllByDocumentoId(int documentoId) {
        Sort sort = Sort.by(Direction.DESC, "fechaVersion");
        return versionRepository.findAllByDocumentoId(documentoId, sort);
    }




}
