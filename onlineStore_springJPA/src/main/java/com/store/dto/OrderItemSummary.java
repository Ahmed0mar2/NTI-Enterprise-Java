package com.store.dto;

import java.math.BigDecimal;

public record OrderItemSummary(Long productId, String productName, int quantity,
                               BigDecimal unitPrice, BigDecimal total) {
}
