package com.si.apirest.service;

import org.springframework.stereotype.Service;

import com.si.apirest.entity.PermissionEntity;
import com.si.apirest.entity.RoleEntity;
import com.si.apirest.entity.Tenant;
import com.si.apirest.enums.Permission;
import com.si.apirest.enums.Role;
import com.si.apirest.exceptions.NotFoundException;
import com.si.apirest.repository.PermissionRepository;
import com.si.apirest.repository.RolRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RolPermissionService {
    
    private final RolRepository rolRepository;

    private final PermissionRepository permissionRepository;


    public RoleEntity asignarPermisoARol(int idRol, int idPermiso) {
        PermissionEntity permissionEntity = permissionRepository.findById(idPermiso)
        .orElseThrow(()->new NotFoundException("Permiso con la id " + idPermiso + "no encontrado."));
        RoleEntity roleEntity = rolRepository.findById(idRol)
        .orElseThrow(()-> new NotFoundException("Rol con la id " + idRol + "no encontrado."));

        roleEntity.getPermissions().add(permissionEntity);
        
        return roleEntity;
    }

    public RoleEntity eliminarPermisoDeRol(int idRol, int idPermiso) {
        PermissionEntity permissionEntity = permissionRepository.findById(idPermiso)
        .orElseThrow(()->new NotFoundException("Permiso con la id " + idPermiso + "no encontrado."));
        RoleEntity roleEntity = rolRepository.findById(idRol)
        .orElseThrow(()-> new NotFoundException("Rol con la id " + idRol + "no encontrado."));

        roleEntity.getPermissions().remove(permissionEntity);

        return roleEntity;
    }

    public List<PermissionEntity> obtenerPermisosDeRol(int idRol) {
        RoleEntity roleEntity = rolRepository.findById(idRol)
        .orElseThrow(()-> new NotFoundException("Rol con la id " + idRol + "no encontrado."));
        return roleEntity.getPermissions();
    }

    public RoleEntity asignarPermisosARol(int idRol, List<Integer> idPermisos) {
        RoleEntity roleEntity = rolRepository.findById(idRol)
        .orElseThrow(()-> new NotFoundException("Rol con la id " + idRol + "no encontrado."));

        List<PermissionEntity> permissionEntities = permissionRepository.findAllById(idPermisos);

        roleEntity.getPermissions().addAll(permissionEntities);

        return roleEntity;
    }

    public RoleEntity createRolAdmin(Tenant tenant) {
        RoleEntity rol = new RoleEntity();
        rol.setName(Role.ADMIN.toString());
        List<PermissionEntity> permissions = permissionRepository.findAll();
        rol.setPermissions(permissions);
        rol.setTenantId(tenant.getId());
        return rolRepository.save(rol);
    }

    public RoleEntity createRolUser(Tenant tenant) {
        RoleEntity rol = new RoleEntity();
        rol.setName(Role.USER.toString());
        PermissionEntity permissions = permissionRepository.findByNombre(Permission.VER_DOCUMENTOS.toString());
        List<PermissionEntity> permissionList = new ArrayList<>();
        permissionList.add(permissions);
        rol.setPermissions(permissionList);
        rol.setTenantId(tenant.getId());
        return rolRepository.save(rol);
    }

    public List<PermissionEntity> getAllPermission() {
        return permissionRepository.findAll();
    }

}
