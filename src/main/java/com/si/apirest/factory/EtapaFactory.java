package com.si.apirest.factory;

import org.modelmapper.ModelMapper;

import com.si.apirest.dto.Etapa.EtapaDTO;
import com.si.apirest.entity.Etapa;

public class EtapaFactory {

    public static EtapaDTO createEtapaDTO(Etapa etapa) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(etapa, EtapaDTO.class);
    }
    
    public static Etapa createEtapa(EtapaDTO etapaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(etapaDTO, Etapa.class);
    }

}
