package com.si.apirest.entity;

import java.time.LocalDateTime;

import com.si.apirest.utils.SubscriptionType;
import com.si.apirest.utils.TenantStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
    
    @Enumerated(EnumType.STRING)
    @JoinColumn(nullable = false)
    private TenantStatus status; // ACTIVE, INACTIVE, etc.
    
    @Enumerated(EnumType.STRING)
    @JoinColumn(nullable = false)
    private SubscriptionType subscriptionType;
    private LocalDateTime createdAt;
    
}
