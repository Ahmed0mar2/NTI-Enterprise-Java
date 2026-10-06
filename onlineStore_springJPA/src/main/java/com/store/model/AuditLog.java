package com.store.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditLog extends BaseEntity {
    private String action;
    private Long orderId;
}
