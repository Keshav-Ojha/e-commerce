package org.keshav.order.repository;

import org.keshav.order.orderline.OrderLine;
import org.keshav.order.orderline.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
