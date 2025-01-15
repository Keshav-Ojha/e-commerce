package org.keshav.payment.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        @NotNull(message="customer id must be present")
        String id,
        @NotNull(message="customer first name is mandatory")
        String firstName,
        @NotNull(message="customer last name is mandatory")
        String lastName,
        @Email(message="incorrect email format.expected::someone@example.com")
        String email
) {
}
