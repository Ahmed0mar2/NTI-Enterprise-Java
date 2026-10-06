package com.store.dto;

import com.store.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderSummary(Long orderId, String customerName, OrderStatus status,
                           LocalDateTime orderedAt, BigDecimal total,
                           List<OrderItemSummary> items) {
}
