package com.si.apirest.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.Person.PersonDTO;
import com.si.apirest.dto.Person.PersonDTOupdate;
import com.si.apirest.dto.Person.PersonGetDTO;
import com.si.apirest.dto.Person.PersonMail;
import com.si.apirest.dto.Person.PersonRequest;
import com.si.apirest.entity.Person;
import com.si.apirest.entity.RoleEntity;
import com.si.apirest.enums.Role;
import com.si.apirest.exceptions.NotFoundException;
import com.si.apirest.factory.PersonFactory;
import com.si.apirest.repository.PersonRepository;
import com.si.apirest.repository.RolRepository;
import com.si.apirest.security.util.AESUtil;
import com.si.apirest.utils.TenantContext;

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

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final MailService mailService;

    public void createPerson(PersonMail personRequest) {
        Long tenantId = Long.valueOf(TenantContext.getCurrentTenant());
        Person person = PersonFactory.toPerson(personRequest);
        person.setEnabled(true);
        person.setTenantId(tenantId);
        String password = AESUtil.generateRandomPassword(12);
        person.setContraseña(passwordEncoder.encode(password));
        if (person.getRole() == null) {
            person.setRole(rolRepository.findByName(Role.ROLE_USER.toString()));
        }
        personRepository.save(person);
        
        // Enviar email con credenciales
        mailService.sendEmail(
                personRequest.getUsuario(),
                "Credenciales de acceso",
                "Tu usuario: " + personRequest.getUsuario() + "\nTu contraseña: " + password
        );
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
    public void setRolUser(int idUser, int idRol) {
        Optional<Person> person = personRepository.findById(idUser);
        Optional<RoleEntity> rol = rolRepository.findById(idRol);
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
