package com.training.empmanager.notify;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class SmsNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending: " + message + " through SMS");
    }
}
