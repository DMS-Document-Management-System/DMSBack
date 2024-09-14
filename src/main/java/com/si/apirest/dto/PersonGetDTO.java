package com.si.apirest.dto;


import lombok.Data;

@Data
public class PersonGetDTO {
    
    private int id;

    private String nombre;

    private String usuario;
    
    private String email;

    private String direccion;

    private boolean enabled;

    private RolGetDTO role;

}
