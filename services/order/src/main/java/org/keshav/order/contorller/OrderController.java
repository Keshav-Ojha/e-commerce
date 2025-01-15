package org.keshav.order.contorller;

import jakarta.validation.Valid;
import org.keshav.order.order.OrderRequest;
import org.keshav.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Integer> placeOrder(@RequestBody @Valid OrderRequest request){
        return ResponseEntity.ok(service.createOrder(request));
    }
}
