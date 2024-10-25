package com.si.apirest.exceptions;

public class BitacoraNotFoundException extends RuntimeException {
    public BitacoraNotFoundException(int id) {
        super("Could not find bitacora " + id);
    }
}
