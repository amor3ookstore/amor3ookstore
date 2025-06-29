package com.amorbookstore.controller;

import com.amorbookstore.dto.OrderDTO;
import com.amorbookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/user/{userId}")
    public OrderDTO createOrder(@PathVariable Long userId, @RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO, userId);
    }
}
