package com.si.apirest.dto;

import lombok.Data;

@Data
public class PersonDTO {

    private int id;

    private String nombre;

    private String usuario;
    
    private String email;

    private String direccion;

    private boolean enabled;

}
