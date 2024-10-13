package com.si.apirest.dto.Person;

import lombok.Data;

@Data
public class PersonDTO {

    private int id;

    private String nombre;

    private String usuario;

    private boolean enabled;

}
