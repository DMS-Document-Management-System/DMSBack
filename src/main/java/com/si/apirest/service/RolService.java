package com.si.apirest.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.Rol.RolDTO;
import com.si.apirest.dto.Rol.RolGetDTO;
import com.si.apirest.entity.RoleEntity;
import com.si.apirest.factory.RolFactory;
import com.si.apirest.repository.RolRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolService {
    
    @Autowired
    private final RolRepository rolRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public RolGetDTO crearRol(RolDTO roleEntity) {
        RoleEntity rol= rolRepository.save(modelMapper.map(roleEntity, RoleEntity.class));
        return RolFactory.createRolGetDTO(rol);
    }

    @Transactional
    public RolGetDTO updateRol(int id, RolDTO roleEntity) {
        RoleEntity roleEntityUpdated =  rolRepository.findById(id).map(rol -> {
            if (roleEntity.getName()!= null && !roleEntity.getName().isEmpty())
                rol.setName(roleEntity.getName());
            return rolRepository.save(rol);
        }).orElseThrow(() -> new EntityNotFoundException("Rol not found with id: "+id));
        return RolFactory.createRolGetDTO(roleEntityUpdated);
    }

    public RolGetDTO getRol(int id){
        RoleEntity roleEntity = rolRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Rol not found with id: "+id));
        return RolFactory.createRolGetDTO(roleEntity);
    }

    public Page<RolGetDTO> getAllRol(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,10);
        return rolRepository.findAll(pageable).map(RolFactory::createRolGetDTO);
    }

    public void deleteRol(int id) {
        rolRepository.deleteById(id);
    }

}
