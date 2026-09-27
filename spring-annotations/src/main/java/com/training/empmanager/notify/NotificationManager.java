package com.training.empmanager.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationManager {
    private final List<Notifier> notifierList = new ArrayList<>();

    @Autowired
    public NotificationManager(EmailNotifier emailNotifier, SmsNotifier smsNotifier, PushNotifier pushNotifier) {
        this.notifierList.add(emailNotifier);
        this.notifierList.add(smsNotifier);
        this.notifierList.add(pushNotifier);
    }

    public void sendNotification(String message) {
        for (Notifier notifier : notifierList) {
            notifier.send(message);
        }
    }
}
