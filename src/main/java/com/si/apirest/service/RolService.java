package com.si.apirest.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.RolDTO;
import com.si.apirest.dto.RolGetDTO;
import com.si.apirest.entity.RoleEntity;
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

    public RoleEntity crearRol(RolDTO roleEntity) {
        RoleEntity rol= rolRepository.save(modelMapper.map(roleEntity, RoleEntity.class));
        return rol;
    }

    @Transactional
    public RoleEntity updateRol(int id, RoleEntity roleEntity) {
        System.out.println(roleEntity.getPermissions());
        return rolRepository.findById(id).map(rol -> {
            if(roleEntity.getPermissions()!=null)
                rol.setPermissions(null);
            else{
                if (roleEntity.getName()!= null && !roleEntity.getName().isEmpty())
                    rol.setName(roleEntity.getName());
            }
            modelMapper.map(rol, roleEntity);
            return rolRepository.save(roleEntity);
        }).orElseThrow(() -> new EntityNotFoundException("Rol not found with id: "+id));
    }

    public RoleEntity getRol(int id){
        return rolRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Rol not found with id: "+id));
    }

    public List<RoleEntity> getAllRol() {
        return rolRepository.findAll();
    }

    public void deleteRol(int id) {
        rolRepository.deleteById(id);
    }

    public List<RolGetDTO> getAllRolDTOs() {
        List<RoleEntity> roleEntities = rolRepository.findAll();
        List<RolGetDTO> roles = new ArrayList<>();
        for (RoleEntity roleEntity : roleEntities) {
            roles.add(modelMapper.map(roleEntity, RolGetDTO.class));   
        }
        return roles;
    }

}
