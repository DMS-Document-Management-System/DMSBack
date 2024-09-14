package com.si.apirest.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si.apirest.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findByUsuario(String usuario);
}
