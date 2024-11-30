package com.si.apirest.factory;

import org.modelmapper.ModelMapper;

import com.si.apirest.dto.Person.PersonMail;
import com.si.apirest.dto.Person.PersonRequest;
import com.si.apirest.entity.Person;

public class PersonFactory {
    
    public static Person toPerson(PersonRequest personRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(personRequest, Person.class);
    }

    public static Person toPerson(PersonMail personRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(personRequest, Person.class);
    }

}
