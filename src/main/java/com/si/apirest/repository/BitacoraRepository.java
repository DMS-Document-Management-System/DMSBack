package com.si.apirest.repository;


import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si.apirest.entity.Bitacora;


@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Integer> {
    List<Bitacora> findByFechaBetween(GregorianCalendar startDate, GregorianCalendar endDate);    
}
