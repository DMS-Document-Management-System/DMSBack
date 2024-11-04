package com.si.apirest.factory;

import org.modelmapper.ModelMapper;

import com.si.apirest.dto.Version.VersionDTO;
import com.si.apirest.entity.Version;

public class VersionFactory {
    
    public static Version createVersion(VersionDTO versionDTO) {
        ModelMapper modelMapper = new ModelMapper();

        Version version = modelMapper.map(versionDTO, Version.class);

        return version;
    }

    public static VersionDTO createVersionDTO(Version version) {
        ModelMapper modelMapper = new ModelMapper();

        VersionDTO versionDTO = modelMapper.map(version, VersionDTO.class);

        return versionDTO;
    }

}
