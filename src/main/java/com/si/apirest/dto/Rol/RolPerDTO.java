package com.si.apirest.dto.Rol;

import java.util.List;

import com.si.apirest.dto.Permiso.PermissionDTO;

import lombok.Data;

@Data
public class RolPerDTO {
    private int id;
    private String name;
    private List<PermissionDTO> permissions;
}
