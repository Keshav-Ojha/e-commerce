package org.keshav.order.service;

import lombok.RequiredArgsConstructor;
import org.keshav.order.orderline.OrderLineMapper;
import org.keshav.order.orderline.OrderLineRequest;
import org.keshav.order.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

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
}
