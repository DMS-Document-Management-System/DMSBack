package com.si.apirest.dto;

import java.util.GregorianCalendar;

import lombok.Data;

@Data
public class BitacoraDTO {
    
    private Integer id;
    private GregorianCalendar fecha;
    private String accion;
    private PersonDTO user;
    private String ip;
}
