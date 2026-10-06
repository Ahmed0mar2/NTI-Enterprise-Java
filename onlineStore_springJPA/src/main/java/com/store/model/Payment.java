package com.store.model;

import com.store.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Payment extends BaseEntity{
    @OneToOne(mappedBy = "payment")
    private Order order;
    @Column(precision = 12, scale = 2)
    private BigDecimal amount;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod method;
    private LocalDateTime paidAt = LocalDateTime.now();
}
