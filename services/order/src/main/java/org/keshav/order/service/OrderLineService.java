package org.keshav.order.service;

import lombok.RequiredArgsConstructor;
import org.keshav.order.orderline.OrderLineMapper;
import org.keshav.order.orderline.OrderLineRequest;
import org.keshav.order.orderline.OrderLineResponse;
import org.keshav.order.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        return repository.save(
                mapper.toOrderLine(orderLineRequest))
                .getId();

    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .toList();
    }
}
