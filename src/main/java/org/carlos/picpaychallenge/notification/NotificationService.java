package org.carlos.picpaychallenge.notification;

import org.carlos.picpaychallenge.transaction.Transaction;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationProducer notificationProducer;

    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public void notify(Transaction transaction) {
        this.notificationProducer.sendNotification(transaction);
    }
}
