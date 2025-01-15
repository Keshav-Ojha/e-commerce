package org.keshav.notification.kafka;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keshav.notification.entity.notification.Notification;
import org.keshav.notification.kafka.order.OrderConfirmation;
import org.keshav.notification.kafka.payment.PaymentConfirmation;
import org.keshav.notification.repository.NotificationRepository;
import org.keshav.notification.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.keshav.notification.entity.notification.NotificationType.ORDER_CONFIRMATION;
import static org.keshav.notification.entity.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;

    private final EmailService emailService;

    @KafkaListener(topics="payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming payment confirmation: {}", paymentConfirmation);
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDateTime(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        //send email

        var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();

        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }


    @KafkaListener(topics="order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming order confirmation: {}", orderConfirmation);
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDateTime(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //send email
        var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();

        emailService.sendPaymentSuccessEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference()
        );
    }

}
