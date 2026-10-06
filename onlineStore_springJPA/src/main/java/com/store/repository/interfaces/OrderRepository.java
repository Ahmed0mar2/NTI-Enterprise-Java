package com.store.repository.interfaces;

import com.store.model.Order;
import com.store.model.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
    Optional<Order> findByIdWithItems(Long id);
    List<Order> findByCustomer(Long customerId);
    List<Order> findByStatus(OrderStatus status);
}
