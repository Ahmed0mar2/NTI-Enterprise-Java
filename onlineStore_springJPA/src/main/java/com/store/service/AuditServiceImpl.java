package com.store.service;

import com.store.model.AuditLog;
import com.store.repository.interfaces.AuditLogRepository;
import com.store.service.interfaces.AuditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditServiceImpl implements AuditService {
    private final AuditLogRepository auditLogRepository;

    public AuditServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void record(String action, Long orderId) {
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(action);
        auditLog.setOrderId(orderId);
        auditLogRepository.save(auditLog);
    }
}
