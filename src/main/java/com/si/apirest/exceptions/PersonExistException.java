package com.si.apirest.exceptions;

public class PersonExistException extends RuntimeException{
    
    
    public PersonExistException(String msg) {
        super(msg);
    }
}
