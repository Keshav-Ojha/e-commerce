package org.keshav.notification.kafka.order;

import org.keshav.notification.kafka.payment.PaymentMethod;

import java.math.BigDecimal;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
