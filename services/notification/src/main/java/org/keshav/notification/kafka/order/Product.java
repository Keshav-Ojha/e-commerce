package org.keshav.notification.kafka.order;

import java.math.BigDecimal;

public record Product(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
