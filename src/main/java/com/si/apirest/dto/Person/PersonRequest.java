package com.si.apirest.dto.Person;

import com.si.apirest.dto.Rol.RolGetDTO;

import lombok.Data;

@Data
public class PersonRequest {
    
    private String nombre;

    private String usuario;

    private String contraseña;

    private RolGetDTO role;
}
