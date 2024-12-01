package com.si.apirest.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.si.apirest.entity.Tenant;
import com.si.apirest.repository.TenantRepository;
import com.si.apirest.utils.SubscriptionType;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ipn-handler")
@RequiredArgsConstructor
public class NowPaymentsIPNController {

    private final TenantRepository tenantRepository;

    @PostMapping("/{email}")
    public ResponseEntity<String> handleIPN(@PathVariable String email) {
        Tenant tenant = tenantRepository.findByEmail(email);
        if (tenant == null) {
            return ResponseEntity.badRequest().body("Tenant not found");
        }
        tenant.setSubscriptionType(SubscriptionType.PREMIUM);
        tenantRepository.save(tenant);
        return ResponseEntity.ok("OK");
    }
}
