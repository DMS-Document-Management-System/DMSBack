package com.si.apirest.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.entity.PermissionEntity;
import com.si.apirest.entity.RoleEntity;
import com.si.apirest.service.RolPermissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Tag(name = "Rol Permiso")
public class RolPermissionController {
    private final RolPermissionService rolPermissionService;

    @PostMapping("/rol/{idRol}/permiso/{idPermiso}")
    @Operation(summary = "Asigna un permiso a un solo rol.")
    public RoleEntity asignarPermisoARol(@PathVariable int idRol, @PathVariable int idPermiso) {
        return rolPermissionService.asignarPermisoARol(idRol, idPermiso);
    }
    
    @PostMapping("/rol/{idRol}")
    @Operation(summary = "Asigna una lista de permisos a un rol.")
    public RoleEntity asignarPermisosARol(@PathVariable int idRol, @RequestBody List<Integer> idPermisos) {
        return rolPermissionService.asignarPermisosARol(idRol, idPermisos);
    }
    
    @DeleteMapping("/rol/{idRol}/permiso/{idPermiso}")
    @Operation(summary = "Quita un permiso existente en un rol.")
    public RoleEntity eliminarPermisoDeRol(@PathVariable int idRol, @PathVariable int idPermiso) {
        return rolPermissionService.eliminarPermisoDeRol(idRol, idPermiso);
    }
    
    @GetMapping("/rol/{idRol}/permisos")
    @Operation(summary = "Obtiene todos los permisos de un rol.")
    public List<PermissionEntity> obtenerPermisosDeRol(@PathVariable int idRol) {
        return rolPermissionService.obtenerPermisosDeRol(idRol);
    }

    @GetMapping("/permisos")
    @Operation(summary = "Obtiene todos los permisos.")
    public List<PermissionEntity> obtenerPermisosDeRol() {
        return rolPermissionService.getAllPermission();
    }

}
