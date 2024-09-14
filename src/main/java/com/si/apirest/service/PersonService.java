package com.si.apirest.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.PersonDTO;
import com.si.apirest.dto.PersonDTOupdate;
import com.si.apirest.dto.PersonGetDTO;
import com.si.apirest.dto.RolGetDTO;
import com.si.apirest.entity.Person;
import com.si.apirest.entity.RoleEntity;
import com.si.apirest.exceptions.NotFoundException;
import com.si.apirest.repository.PersonRepository;
import com.si.apirest.repository.RolRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    @Autowired
    private final PersonRepository personRepository;

    @Autowired
    private final RolRepository rolRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public void createPerson(Person person) {
        personRepository.save(person);
    }

    public void updatePerson(PersonDTOupdate person, int id) {
        System.out.println(person);
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person updatedUser = optionalPerson.get();
            modelMapper.map(person, updatedUser);
            personRepository.save(updatedUser);
        }
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getPerson(int id) {
        return personRepository.findById(id);
    }

    public List<PersonDTO> getAllPerson() {
        List<Person> personList = personRepository.findAll(); 
        List<PersonDTO> personDTOs = new ArrayList<>();
        for (Person userPerson : personList) {
            personDTOs.add(modelMapper.map(userPerson, PersonDTO.class));
        }
        return personDTOs;
    }

    public void unableUser(int id) {
        Optional<Person> person= personRepository.findById(id);

        person.ifPresent( user -> {
            user.setEnabled(false);
            personRepository.save(user);
            }
        );
    }

    public void enableUser(int id) {
        Optional<Person> person= personRepository.findById(id);

        person.ifPresent( user -> {
            user.setEnabled(true);
            personRepository.save(user);
            }
        );
    }

    public PersonDTO getUser(String username) {
        Person person = personRepository.findByUsuario(username)
        .orElseThrow( () -> new UsernameNotFoundException("PersonGet: Usuario no encontrado"));
        return modelMapper.map(person, PersonDTO.class);
    }

    @Transactional
    public void setRolUser(int idUser, RolGetDTO rolDto) {
        Optional<Person> person = personRepository.findById(idUser);
        Optional<RoleEntity> rol = rolRepository.findById(rolDto.getId());
        if (person.isPresent() && rol.isPresent()){
            person.get().setRole(rol.get());
        }else{
            throw new NotFoundException("No se ha encontrado rol o usuario");
        }

    }

    public List<PersonGetDTO> getAllPersonTable() {
        List<Person> personList = personRepository.findAll(); 
        List<PersonGetDTO> personDTOs = new ArrayList<>();
        for (Person userPerson : personList) {
            personDTOs.add(modelMapper.map(userPerson, PersonGetDTO.class));
        }
        return personDTOs;
    }

}
