package org.keshav.order.controller;

import jakarta.validation.Valid;
import org.keshav.order.order.OrderRequest;
import org.keshav.order.order.OrderResponse;
import org.keshav.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(service.fetchAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Integer orderId){
        return ResponseEntity.ok(service.getById(orderId));
    }

//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<List<OrderResponse>> findByCustomerId(@PathVariable Integer customerId){
//        return ResponseEntity.ok(service.getByCustomerId(customerId));
//    }

}
