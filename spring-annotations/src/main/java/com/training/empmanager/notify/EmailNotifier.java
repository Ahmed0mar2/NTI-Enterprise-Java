package com.training.empmanager.notify;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending: " + message + " through email...");
    }
}
