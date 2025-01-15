package org.keshav.order.kafka;

import org.keshav.order.customer.CustomerResponse;
import org.keshav.order.order.PaymentMethod;
import org.keshav.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
