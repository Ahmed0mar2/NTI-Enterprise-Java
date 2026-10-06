package com.store.service.interfaces;

public interface AuditService {
    void record(String action, Long orderId);
}
