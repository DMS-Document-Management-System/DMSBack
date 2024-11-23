package com.si.apirest.entity;

import java.util.GregorianCalendar;

import org.hibernate.annotations.Filter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Bitacora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private GregorianCalendar fecha;
    private String accion;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Person user;
    private String ip;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = true)
    private Tenant tenant;
    
}
