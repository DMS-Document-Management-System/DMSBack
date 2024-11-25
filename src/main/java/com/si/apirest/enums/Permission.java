package com.si.apirest.enums;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Permission {
    VER_BITACORA,
    VER_DOCUMENTOS,
    VER_CATEGORIA,
    VER_INVENTARIO,
    VER_ROLES,
    VER_PERMISOS,
    VER_USUARIOS,
    VER_DASHBOARD,
    VER_HOME;

    static {
        permissionListEnums = Arrays.stream(values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    private static final List<String> permissionListEnums;

    // Método para obtener la lista de nombres de permisos
    public static List<String> getAllPermissionNames() {
        return permissionListEnums;
    }

}
