package com.store.service;

import com.store.dto.OrderItemSummary;
import com.store.dto.OrderSummary;
import com.store.exception.*;
import com.store.model.Order;
import com.store.model.OrderItem;
import com.store.model.Payment;
import com.store.model.Product;
import com.store.model.enums.OrderStatus;
import com.store.model.enums.PaymentMethod;
import com.store.repository.interfaces.CustomerRepository;
import com.store.repository.interfaces.OrderRepository;
import com.store.repository.interfaces.ProductRepository;
import com.store.service.interfaces.AuditService;
import com.store.service.interfaces.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final AuditService auditService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerRepository customerRepository,
                            ProductRepository productRepository,
                            AuditService auditService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.auditService = auditService;
    }

    @Override
    @Transactional
    public Order placeOrder(Long customerId, Map<Long, Integer> productQuantities) {
        if (productQuantities == null || productQuantities.isEmpty()) {
            throw new IllegalArgumentException("An order must contain at least one product");
        }

        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        Order order = new Order();
        order.setCustomer(customer);

        for (var line : productQuantities.entrySet()) {
            int quantity = line.getValue() == null ? 0 : line.getValue();
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }

            Product product = productRepository.findByIdForUpdate(line.getKey())
                    .orElseThrow(() -> new ProductNotFoundException(line.getKey()));
            if (product.getStock() < quantity) {
                throw new InsufficientStockException(product.getId(), quantity, product.getStock());
            }

            product.setStock(product.getStock() - quantity);
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setUnitPrice(product.getPrice());
            order.addOrderItem(item);
        }

        customer.addOrder(order);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void pay(Long orderId, PaymentMethod method) {
        Order order = getOrder(orderId);
        if (order.getStatus() != OrderStatus.NEW) {
            throw new InvalidOrderStateException("Only a NEW order can be paid");
        }
        if (method == null) {
            throw new IllegalArgumentException("Payment method is required");
        }
        Payment payment = new Payment();
        payment.setAmount(order.getTotal());
        payment.setMethod(method);
        payment.setPaidAt(LocalDateTime.now());
        order.setPayment(payment);
        order.setStatus(OrderStatus.PAID);
        auditService.record("ORDER_PAID", orderId);
    }

    @Override
    @Transactional
    public void ship(Long orderId) {
        Order order = getOrder(orderId);
        if (order.getStatus() != OrderStatus.PAID) {
            throw new InvalidOrderStateException("Only a PAID order can be shipped");
        }
        order.setStatus(OrderStatus.SHIPPED);
        auditService.record("ORDER_SHIPPED", orderId);
    }

    @Override
    @Transactional
    public void cancel(Long orderId) {
        Order order = getOrder(orderId);
        if (order.getStatus() != OrderStatus.NEW && order.getStatus() != OrderStatus.PAID) {
            throw new InvalidOrderStateException("Only NEW or PAID orders can be cancelled");
        }
        for (OrderItem item : order.getItems()) {
            Product product = productRepository.findByIdForUpdate(item.getProduct().getId())
                    .orElseThrow(() -> new ProductNotFoundException(item.getProduct().getId()));
            product.setStock(product.getStock() + item.getQuantity());
        }
        order.setStatus(OrderStatus.CANCELLED);
        auditService.record("ORDER_CANCELLED", orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderSummary getOrderSummary(Long orderId) {
        Order order = orderRepository.findByIdWithItems(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        List<OrderItemSummary> items = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            items.add(new OrderItemSummary(item.getProduct().getId(), item.getProduct().getName(),
                    item.getQuantity(), item.getUnitPrice(),
                    item.getUnitPrice().multiply(java.math.BigDecimal.valueOf(item.getQuantity()))));
        }
        return new OrderSummary(order.getId(), order.getCustomer().getName(), order.getStatus(),
                order.getOrderedAt(), order.getTotal(), items);
    }

    private Order getOrder(Long orderId) {
        return orderRepository.findByIdWithItems(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
