package org.keshav.payment.repository;

import org.keshav.payment.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepsitory extends JpaRepository<Payment, Integer> {
}
