package com.si.apirest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Person.PersonDTO;
import com.si.apirest.service.PersonService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Tag(name = "User")
public class PersonController {
    
    private final PersonService personService;

    @PutMapping("/enable/{id}")
    public void enableUser(@PathVariable int id) {
        personService.enableUser(id);
    }

    @PutMapping("/unable/{id}")
    public void unableUser(@PathVariable int id) {
        personService.unableUser(id);
    }

    @GetMapping("/{username}")
    public PersonDTO getUserByUsername(@PathVariable String username) {
        return personService.getUser(username);
    }

    @PutMapping("/{idUser}/rol/{idRol}")
    public void setRolUser(int idUser, int idRol) {
        personService.setRolUser(idUser, idRol);
    }

}
