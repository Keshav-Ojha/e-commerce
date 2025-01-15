package org.keshav.order.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.keshav.order.customer.CustomerClient;
import org.keshav.order.exception.BusinessException;
import org.keshav.order.kafka.OrderConfirmation;
import org.keshav.order.kafka.OrderProducer;
import org.keshav.order.order.OrderMapper;
import org.keshav.order.order.OrderRequest;
import org.keshav.order.order.OrderResponse;
import org.keshav.order.orderline.OrderLineRequest;
import org.keshav.order.product.ProductClient;
import org.keshav.order.product.PurchaseRequest;
import org.keshav.order.repository.OrderRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRespository repository;

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderMapper mapper;

    private final OrderLineService orderLineService;

    private final OrderProducer orderProducer;

//    public OrderService(OrderRespository repository, CustomerClient customerClient, ProductClient productClient, OrderMapper mapper, OrderLineService orderLineService) {
//        this.repository = repository;
//        this.customerClient = customerClient;
//        this.productClient = productClient;
//        this.mapper = mapper;
//        this.orderLineService = orderLineService;
//    }

    public Integer createOrder(@Valid OrderRequest request) {
        //check the customer --> open feign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order::Customer not found"));

        //purchase the products --> product-ms (rest template)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        //persist order
        var order = this.repository.save(mapper.toOrder(request));

        //persist order lines
        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //todo start payment process --> payment-ms

        //send the order confirmation to notification-ms(kafka)

        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts
        ));

        return order.getId();
    }

    public List<OrderResponse> fetchAllOrders() {
        return repository.findAll()
                .stream()
                .map(mapper::toOrderResponse)
                .toList();

    }

    public OrderResponse getById(Integer orderId) {
        return this.repository.findById(orderId)
                .map(mapper::toOrderResponse)
                .orElseThrow(
                () -> new EntityNotFoundException("Cannot find order with id::" + orderId)
        );
    }
}
