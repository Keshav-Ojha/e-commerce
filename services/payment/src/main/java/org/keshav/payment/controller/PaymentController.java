package org.keshav.payment.controller;

import lombok.RequiredArgsConstructor;
import org.keshav.payment.entity.payment.PaymentRequest;
import org.keshav.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<Integer> createPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(service.createPayment(request));
    }
}
