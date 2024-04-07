package org.carlos.picpaychallenge.notification;

import org.carlos.picpaychallenge.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
    private final RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder, @Value("${notification.service.url}") String baseUrl) {
        this.restClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
    public void receiveNotification(Transaction transaction) {
        LOGGER.info("Received transaction - {}", transaction);
        ResponseEntity<Notification> response = restClient.get().retrieve().toEntity(Notification.class);

        Notification body = response.getBody();
        if (response.getStatusCode().isError() || body == null || !body.message()) {
            throw new NotificationException("Error sending notification!");
        }

        LOGGER.info("Notification sent - {}", transaction);
    }
}
