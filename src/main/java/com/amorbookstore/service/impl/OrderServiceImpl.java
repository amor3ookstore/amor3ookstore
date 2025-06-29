package com.amorbookstore.service.impl;

import com.amorbookstore.dto.OrderDTO;
import com.amorbookstore.dto.OrderItemDTO;
import com.amorbookstore.entity.*;
import com.amorbookstore.exception.ResourceNotFoundException;
import com.amorbookstore.repository.BookRepository;
import com.amorbookstore.repository.OrderItemRepository;
import com.amorbookstore.repository.OrderRepository;
import com.amorbookstore.repository.UserRepository;
import com.amorbookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private OrderDTO mapToDTO(Order order) {
        List<OrderItemDTO> items = order.getItems().stream().map(item ->
                OrderItemDTO.builder()
                        .id(item.getId())
                        .orderId(order.getId())
                        .bookId(item.getBook().getId())
                        .bookTitle(item.getBook().getTitle())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build()
        ).collect(Collectors.toList());

        return OrderDTO.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .items(items)
                .total(order.getTotal())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt().toString())
                .build();
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return mapToDTO(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = Order.builder()
                .user(user)
                .status("PENDING")
                .createdAt(new Date())
                .build();

        HashSet<OrderItem> items = new HashSet<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            Book book = bookRepository.findById(itemDTO.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
            if (book.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("Not enough stock for book: " + book.getTitle());
            }
            book.setStock(book.getStock() - itemDTO.getQuantity());
            OrderItem item = OrderItem.builder()
                    .order(order)
                    .book(book)
                    .quantity(itemDTO.getQuantity())
                    .price(book.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())))
                    .build();
            items.add(item);
            total = total.add(item.getPrice());
        }
        order.setItems(items);
        order.setTotal(total);
        Order saved = orderRepository.save(order);
        items.forEach(orderItemRepository::save);
        return mapToDTO(saved);
    }
}
