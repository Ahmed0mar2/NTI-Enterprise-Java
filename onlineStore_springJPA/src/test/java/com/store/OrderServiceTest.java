package com.store;

import com.store.config.JpaConfig;
import com.store.exception.InsufficientStockException;
import com.store.model.Address;
import com.store.model.enums.OrderStatus;
import com.store.model.enums.PaymentMethod;
import com.store.repository.interfaces.ProductRepository;
import com.store.service.interfaces.CustomerService;
import com.store.service.interfaces.OrderService;
import com.store.service.interfaces.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {
    private static AnnotationConfigApplicationContext context;
    private static CustomerService customerService;
    private static ProductService productService;
    private static OrderService orderService;
    private static ProductRepository productRepository;
    private static final AtomicInteger sequence = new AtomicInteger();

    private Long customerId;
    private Long productId;

    @BeforeAll
    static void startContext() {
        context = new AnnotationConfigApplicationContext(JpaConfig.class);
        customerService = context.getBean(CustomerService.class);
        productService = context.getBean(ProductService.class);
        orderService = context.getBean(OrderService.class);
        productRepository = context.getBean(ProductRepository.class);
    }

    @AfterAll
    static void closeContext() {
        context.close();
    }

    @BeforeEach
    void createTestData() {
        int id = sequence.incrementAndGet();
        var customer = customerService.register(
                "Customer " + id,
                "customer" + id + "@example.com",
                new Address("Street", "Cairo", "Egypt"));
        var product = productService.addProduct(
                "SKU-" + id, "Product " + id, new BigDecimal("10.00"), 3);
        customerId = customer.getId();
        productId = product.getId();
    }

    @Test
    void placesPaysAndShipsOrder() {
        var order = orderService.placeOrder(customerId, Map.of(productId, 2));

        orderService.pay(order.getId(), PaymentMethod.CARD);
        assertEquals(OrderStatus.PAID,
                orderService.getOrderSummary(order.getId()).status());

        orderService.ship(order.getId());
        assertEquals(OrderStatus.SHIPPED,
                orderService.getOrderSummary(order.getId()).status());
        assertEquals(1, productRepository.findById(productId).orElseThrow().getStock());
    }

    @Test
    void rollsBackStockWhenOrderCannotBePlaced() {
        assertThrows(InsufficientStockException.class,
                () -> orderService.placeOrder(customerId, Map.of(productId, 4)));

        assertEquals(3, productRepository.findById(productId).orElseThrow().getStock());
    }

    @Test
    void cancelRestoresStock() {
        var order = orderService.placeOrder(customerId, Map.of(productId, 2));
        orderService.cancel(order.getId());

        assertEquals(OrderStatus.CANCELLED,
                orderService.getOrderSummary(order.getId()).status());
        assertEquals(3, productRepository.findById(productId).orElseThrow().getStock());
    }
}
