package com.si.apirest.security.auth;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.Tenant.TenantRequest;
import com.si.apirest.entity.Person;
import com.si.apirest.entity.RoleEntity;
import com.si.apirest.entity.Tenant;
import com.si.apirest.factory.TenantFactory;
import com.si.apirest.repository.PersonRepository;
import com.si.apirest.repository.TenantRepository;
import com.si.apirest.service.MailService;
import com.si.apirest.service.RolPermissionService;
import com.si.apirest.utils.SubscriptionType;
import com.si.apirest.utils.TenantStatus;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {
    private final TenantRepository tenantRepository;
    private final PasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;
    private final MailService mailService;
    private final RolPermissionService rolPermissionService;

    @Transactional
    public void createNewTenant(TenantRequest tenantRequest) {
        // Verificar si ya existe el tenant
        Tenant tenantFound = tenantRepository.findByEmail(tenantRequest.getEmail());
        if (tenantFound != null) {
            throw new RuntimeException("Tenant already exists");
        }

        // Crear y guardar el Tenant
        Tenant tenant = TenantFactory.toTenant(tenantRequest);
        tenant.setStatus(TenantStatus.ACTIVE);
        tenant.setSubscriptionType(SubscriptionType.FREE_TRIAL);
        tenant.setCreatedAt(LocalDateTime.now());
        Tenant tenantSaved = tenantRepository.save(tenant);

        // Crear y guardar el Usuario Admin
        RoleEntity roleEntity = rolPermissionService.createRolAdmin(tenantSaved);
        rolPermissionService.createRolUser(tenantSaved);

        // Generar credenciales
        String password = generateRandomPassword(12); // Longitud de 12 caracteres
        String username = tenantRequest.getEmail();

        Person adminUser = Person.builder()
                .usuario(username)
                .nombre(tenantRequest.getName())
                .contraseña(passwordEncoder.encode(password))
                .role(roleEntity)
                .enabled(true)
                .build();
        adminUser.setTenantId(tenantSaved.getId());
        personRepository.save(adminUser);

        // Enviar email con credenciales
        mailService.sendEmail(
                username,
                "Credenciales de acceso",
                "Tu usuario: " + username + "\nTu contraseña: " + password
        );
    }

    private String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, length);
    }

    public Tenant findByEmail(String email) {
        return tenantRepository.findByEmail(email);
    }

}
