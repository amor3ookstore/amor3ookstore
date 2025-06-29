package com.amorbookstore.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private Long id;
    private Long orderId;
    private Long bookId;
    private String bookTitle;
    private int quantity;
    private BigDecimal price;
}
