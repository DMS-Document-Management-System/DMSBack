package com.si.apirest.entity;

import java.util.List;

import org.hibernate.annotations.Filter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ROL")
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rol_permiso",
        joinColumns = @JoinColumn(name = "rol_id"),
        inverseJoinColumns =@JoinColumn(name = "permiso_id")
    )
    private List<PermissionEntity> permissions;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = true)
    private Tenant tenant;

}
