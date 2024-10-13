package com.si.apirest.dto.Bitacora;

import java.util.GregorianCalendar;

import com.si.apirest.dto.Person.PersonDTO;

import lombok.Data;

@Data
public class BitacoraDTO {
    
    private Integer id;
    private GregorianCalendar fecha;
    private String accion;
    private PersonDTO user;
    private String ip;
}
