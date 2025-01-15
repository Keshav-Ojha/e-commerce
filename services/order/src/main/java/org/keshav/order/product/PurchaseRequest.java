package org.keshav.order.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull
        Integer productId,
        @Positive(message="Quantity is mandatory")
        double quantity
) {
}
