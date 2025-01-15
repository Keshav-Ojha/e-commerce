package org.keshav.notification.entity.notification;

import lombok.*;
import org.keshav.notification.kafka.order.OrderConfirmation;
import org.keshav.notification.kafka.payment.PaymentConfirmation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;

    private NotificationType type;

    private LocalDateTime notificationDateTime;

    private OrderConfirmation orderConfirmation;

    private PaymentConfirmation paymentConfirmation;
}
