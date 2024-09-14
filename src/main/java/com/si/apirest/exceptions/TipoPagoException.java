package com.si.apirest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TipoPagoException extends RuntimeException{
    public TipoPagoException(String message){ 
        super(message);
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class TipoPagoNotFoundException extends RuntimeException {
    public TipoPagoNotFoundException(int id) {
        super("TipoPago not found with id: " + id);
    }
}

