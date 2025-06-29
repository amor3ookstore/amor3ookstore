package com.amorbookstore.service;

import com.amorbookstore.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrdersByUser(Long userId);
    OrderDTO getOrderById(Long id);
    OrderDTO createOrder(OrderDTO orderDTO, Long userId);
}
