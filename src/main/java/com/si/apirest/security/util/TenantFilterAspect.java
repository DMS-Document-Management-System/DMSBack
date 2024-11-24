package com.si.apirest.security.util;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.si.apirest.utils.TenantContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Aspect
@Component
public class TenantFilterAspect {

    @PersistenceContext
    private EntityManager entityManager;

    @Before("execution(* com.si.apirest.service..*(..))") // Aplica a todos los métodos de servicios
    public void enableTenantFilter() {
        String tenantId = TenantContext.getCurrentTenant();
        System.out.println("Verificando si se ejecuta el aspecto... " + tenantId);
        if (tenantId != null) {
            try {
                Long tenantIdLong = Long.valueOf(tenantId); // Conversión a Long
                Session session = entityManager.unwrap(Session.class);
                Filter filter = session.enableFilter("tenantFilter");
                filter.setParameter("tenantId", tenantIdLong); // Parámetro como Long
                System.out.println("Se colocó el filtro: " + tenantId);
            } catch (NumberFormatException e) {
                System.err.println("El tenantId no es un número válido: " + tenantId);
                throw new IllegalArgumentException("Tenant ID inválido: " + tenantId, e);
            }
        }
    }
}
