package com.store;

import com.store.config.JpaConfig;
import com.store.dto.OrderSummary;
import com.store.model.Address;
import com.store.model.Customer;
import com.store.model.Product;
import com.store.model.enums.PaymentMethod;
import com.store.service.interfaces.CustomerService;
import com.store.service.interfaces.OrderService;
import com.store.service.interfaces.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(JpaConfig.class)) {
            CustomerService customerService = context.getBean(CustomerService.class);
            ProductService productService = context.getBean(ProductService.class);
            OrderService orderService = context.getBean(OrderService.class);

            Customer customer = customerService.register(
                    "Ahmed Omar",
                    "ahmed@example.com",
                    new Address("Main Street", "Cairo", "Egypt"));
            Product product = productService.addProduct(
                    "LAP-001", "Laptop", new BigDecimal("1200.00"), 5);

            var order = orderService.placeOrder(
                    customer.getId(), Map.of(product.getId(), 2));
            orderService.pay(order.getId(), PaymentMethod.CARD);
            printSummary(orderService.getOrderSummary(order.getId()));

            orderService.ship(order.getId());
            System.out.println("Final status: "
                    + orderService.getOrderSummary(order.getId()).status());
        }
    }

    private static void printSummary(OrderSummary summary) {
        System.out.println("Order #" + summary.orderId()
                + " for " + summary.customerName());
        System.out.println("Items: " + summary.items().size());
        System.out.println("Total: " + summary.total());
        System.out.println("Status: " + summary.status());
    }
}
