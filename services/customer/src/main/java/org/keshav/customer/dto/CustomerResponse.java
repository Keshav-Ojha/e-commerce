package org.keshav.customer.dto;

import org.keshav.customer.entity.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {

}