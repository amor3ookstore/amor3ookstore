package com.amorbookstore.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private int stock;
    private String description;
    private String imageUrl;
    private Long categoryId;
    private String categoryName;
}
