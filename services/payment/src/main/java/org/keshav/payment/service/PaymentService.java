package org.keshav.payment.service;

import lombok.RequiredArgsConstructor;
import org.keshav.payment.entity.payment.PaymentMapper;
import org.keshav.payment.entity.payment.PaymentRequest;
import org.keshav.payment.repository.PaymentRepsitory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepsitory repository;
    private final PaymentMapper mapper;

    public Integer createPayment(PaymentRequest request) {
        return repository.save(mapper.toPayment(request)).getId();
    }
}
