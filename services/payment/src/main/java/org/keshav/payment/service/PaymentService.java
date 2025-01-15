package org.keshav.payment.service;

import lombok.RequiredArgsConstructor;
import org.keshav.payment.entity.payment.PaymentMapper;
import org.keshav.payment.entity.payment.PaymentRequest;
import org.keshav.payment.notification.NotificationProducer;
import org.keshav.payment.notification.PaymentNotificationRequest;
import org.keshav.payment.repository.PaymentRepsitory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepsitory repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));

        //send payment notification --> to notification-ms (kafka)

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );

        return payment.getId();

    }
}
