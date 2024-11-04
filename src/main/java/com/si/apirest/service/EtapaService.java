package com.si.apirest.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.Etapa.EtapaDTO;
import com.si.apirest.entity.Etapa;
import com.si.apirest.factory.EtapaFactory;
import com.si.apirest.repository.EtapaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtapaService {
    
    private final EtapaRepository etapaRepository;

    public EtapaDTO createEtapa(EtapaDTO etapaDTO) {
        Etapa etapa = EtapaFactory.createEtapa(etapaDTO);
        
        return EtapaFactory.createEtapaDTO(etapaRepository.save(etapa));
    }

    public List<EtapaDTO> findAllEtapaDTO(int idWorkflow) {
        Sort sort = Sort.by(Direction.ASC,"orden" );
        return etapaRepository.findAllByWorkflowId(idWorkflow, sort).stream().map(EtapaFactory::createEtapaDTO).toList();
    }

}
