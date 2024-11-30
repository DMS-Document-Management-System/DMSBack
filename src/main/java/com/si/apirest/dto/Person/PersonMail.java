package com.si.apirest.dto.Person;

import com.si.apirest.dto.Rol.RolGetDTO;
import com.si.apirest.projection.ForeignKey;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class PersonMail {
    
    private String nombre;
    
    @Email(message = "El campo email no puede estar vacio.")
    private String usuario;

    @Schema(implementation = ForeignKey.class)
    private RolGetDTO role;
}
