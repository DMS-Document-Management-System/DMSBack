package com.si.apirest.factory;

import org.modelmapper.ModelMapper;

import com.si.apirest.dto.Rol.RolDTO;
import com.si.apirest.dto.Rol.RolGetDTO;
import com.si.apirest.dto.Rol.RolPerDTO;
import com.si.apirest.entity.RoleEntity;

public class RolFactory {
    public static RoleEntity createRolEntity(RolDTO rolDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(rolDTO,RoleEntity.class);
    }

    public static RoleEntity createRolEntity(RolGetDTO rolGetDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(rolGetDTO,RoleEntity.class);
    }

    public static RoleEntity createRolEntity(RolPerDTO rolGetDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(rolGetDTO,RoleEntity.class);
    }

    public static RolGetDTO createRolGetDTO(RoleEntity roleEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleEntity,RolGetDTO.class);
    }
    

}
