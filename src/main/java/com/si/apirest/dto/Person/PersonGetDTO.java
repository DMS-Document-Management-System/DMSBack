package com.si.apirest.dto.Person;


import com.si.apirest.dto.Rol.RolGetDTO;

import lombok.Data;

@Data
public class PersonGetDTO {
    
    private int id;

    private String nombre;

    private String usuario;

    private boolean enabled;

    private RolGetDTO role;

}
