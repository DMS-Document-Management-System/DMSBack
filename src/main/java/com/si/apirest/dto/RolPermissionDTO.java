package com.si.apirest.dto;

import java.util.List;

import com.si.apirest.entity.PermissionEntity;
import com.si.apirest.entity.RoleEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolPermissionDTO {
    private RoleEntity rol;
    private List<PermissionEntity> permisos;
}
