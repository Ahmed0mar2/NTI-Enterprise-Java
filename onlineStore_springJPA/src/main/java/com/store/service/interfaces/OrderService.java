package com.store.service.interfaces;

import com.store.model.Order;
import com.store.model.enums.PaymentMethod;
import com.store.dto.OrderSummary;

import java.util.Map;

public interface OrderService {
    Order placeOrder(Long customerId, Map<Long, Integer> productQuantities);
    void pay(Long orderId, PaymentMethod method);
    void ship(Long orderId);
    void cancel(Long orderId);
    OrderSummary getOrderSummary(Long orderId);
}
