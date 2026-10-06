package com.store.repository;

import com.store.model.AuditLog;
import com.store.repository.interfaces.AuditLogRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AuditLogRepositoryImpl implements AuditLogRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public AuditLog save(AuditLog auditLog) {
        em.persist(auditLog);
        return auditLog;
    }
}
