package com.store.repository.interfaces;

import com.store.model.AuditLog;

public interface AuditLogRepository {
    AuditLog save(AuditLog auditLog);
}
