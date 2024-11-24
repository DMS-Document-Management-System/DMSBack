package com.si.apirest.entity;

import java.util.GregorianCalendar;

import com.si.apirest.security.util.TenantEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper=false)
@EntityListeners(TenantEntityListener.class)
public class Bitacora extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private GregorianCalendar fecha;
    private String accion;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Person user;
    private String ip;

    
}
